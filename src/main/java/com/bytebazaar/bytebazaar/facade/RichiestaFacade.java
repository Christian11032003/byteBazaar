package com.bytebazaar.bytebazaar.facade;

import com.bytebazaar.bytebazaar.dto.request.richiesta.AcceptOrRejectRequestDTO;
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

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RichiestaFacade
{

    private final RichiestaService serviceRichiesta;

    private final UtenteRepository utenteRepo;

    public boolean registrazioneRichiesta(Utente u)
    {

        Richiesta r = new Richiesta();
        r.setUtente(u);
        r.setStato(Stato.RICHIESTA);
        serviceRichiesta.salva(r);
        return true;
    }


    public boolean richiesta(Utente u) {


        Optional<Richiesta> r = serviceRichiesta.findByUtenteUsername(u.getUsername());



        if (r.isEmpty()) {
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



    public boolean modifyTheRequest(AcceptOrRejectRequestDTO request) {

        Richiesta r = serviceRichiesta.findByIdrichiesta(request.getIdRichiesta());


        Utente ur = r.getUtente();


        if (request.getStato().equals(Stato.ACCETTATO))
        {
            if(ur.getRuolo() == Ruolo.VENDITORE) {
                r.setStato(Stato.ACCETTATO);
            }

            else if(ur.getRuolo() == Ruolo.CLIENTE)
            {
                ur.setRuolo(Ruolo.VENDITORE);
                r.setStato(Stato.ACCETTATO);
            }


        }

        else if (request.getStato().equals(Stato.RIFIUTATO))
        {
            if(ur.getRuolo() == Ruolo.VENDITORE)
            {
                ur.setRuolo(Ruolo.CLIENTE);
                r.setStato(Stato.RIFIUTATO);
            }

            else if(ur.getRuolo() == Ruolo.CLIENTE)
            {
                r.setStato(Stato.RIFIUTATO);
            }

            }
        else
        {
            throw new NotFoundException("Errore");
        }

        // Aggiorna l'utente e la richiesta nel repository
        utenteRepo.save(ur);
        serviceRichiesta.salva(r);

        return true;
    }
}



