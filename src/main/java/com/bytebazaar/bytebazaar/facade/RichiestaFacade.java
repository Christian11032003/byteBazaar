package com.bytebazaar.bytebazaar.facade;

import com.bytebazaar.bytebazaar.dto.request.AcceptOrRejectRequest;
import com.bytebazaar.bytebazaar.exception.messaggiException.NotFoundException;
import com.bytebazaar.bytebazaar.exception.messaggiException.UnAuthorizedException;
import com.bytebazaar.bytebazaar.model.Richiesta;
import com.bytebazaar.bytebazaar.model.Ruolo;
import com.bytebazaar.bytebazaar.model.Stato;
import com.bytebazaar.bytebazaar.model.Utente;
import com.bytebazaar.bytebazaar.repository.UtenteRepository;
import com.bytebazaar.bytebazaar.service.definition.RichiestaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class RichiestaFacade
{

    private final RichiestaService serviceRichiesta;

    private final UtenteRepository utenteRepo;

    public boolean registrazioneRichiesta(Utente u) {
        Richiesta r = new Richiesta();
        r.setUtente(u);
        r.setStato(Stato.RICHIESTA);
        serviceRichiesta.salva(r);
        return true;
    }


    public boolean richiesta(Utente u) {

        Richiesta r = serviceRichiesta.findByUtenteUsername(u.getUsername());

        if (r == null) {
            Richiesta newRichiesta = new Richiesta();
            newRichiesta.setUtente(u);
            newRichiesta.setStato(Stato.RICHIESTA);
            serviceRichiesta.salva(newRichiesta);
            return true;
        }

        else
        {
            throw new UnAuthorizedException("Richiesta già somministrata, attendere l'approvazione di un admin");
        }

    }



    public boolean modifyTheRequest(Utente u, AcceptOrRejectRequest request) {

        Richiesta r = serviceRichiesta.findByIdrichiesta(request.getIdrichiesta());


        Utente ut = r.getUtente();


        if (request.getStato().equals(Stato.ACCETTATO))
        {
            if(ut.getRuolo() == Ruolo.VENDITORE) {
                r.setStato(Stato.ACCETTATO);
            }

            else if(ut.getRuolo() == Ruolo.CLIENTE)
            {
                ut.setRuolo(Ruolo.VENDITORE);
                r.setStato(Stato.ACCETTATO);
            }


        }

        else if (request.getStato().equals(Stato.RIFIUTATO))
        {
            if(ut.getRuolo() == Ruolo.VENDITORE)
            {
                ut.setRuolo(Ruolo.CLIENTE);
                r.setStato(Stato.RIFIUTATO);
            }

            else if(ut.getRuolo() == Ruolo.CLIENTE)
            {
                r.setStato(Stato.RIFIUTATO);
            }

            }
        else
        {
            throw new NotFoundException("Errore");
        }

        // Aggiorna l'utente e la richiesta nel repository
        utenteRepo.save(u);
        serviceRichiesta.salva(r);

        return true;
    }
}



