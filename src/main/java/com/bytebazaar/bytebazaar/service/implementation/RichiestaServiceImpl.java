package com.bytebazaar.bytebazaar.service.implementation;

import com.bytebazaar.bytebazaar.dto.request.AcceptOrRejectRequest;
import com.bytebazaar.bytebazaar.exception.messaggiException.NotFoundException;
import com.bytebazaar.bytebazaar.exception.messaggiException.UnAuthorizedException;
import com.bytebazaar.bytebazaar.model.Richiesta;
import com.bytebazaar.bytebazaar.model.Ruolo;
import com.bytebazaar.bytebazaar.model.Stato;
import com.bytebazaar.bytebazaar.model.Utente;
import com.bytebazaar.bytebazaar.repository.RichiestaRepository;
import com.bytebazaar.bytebazaar.repository.UtenteRepository;
import com.bytebazaar.bytebazaar.service.definition.RichiestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RichiestaServiceImpl implements RichiestaService
{

    @Autowired
    private RichiestaRepository richiestaRepo;

    @Autowired
    private UtenteRepository utenteRepo;


    public boolean registrazioneRichiesta(Utente u)
    {
        Richiesta r = new Richiesta();
        r.setUtente(u);
        r.setStato(Stato.RICHIESTA);
        richiestaRepo.save(r);
        return true;
    }


    /*public boolean richiesta(Utente u)
    {

        Optional<Richiesta> richiestaOptional = richiestaRepo.findByUtente_Username(u.getUsername());

        if (richiestaOptional.isEmpty()) {
            Richiesta r = new Richiesta();
            r.setUtente(u);
            r.setStato(Stato.RICHIESTA);
            richiestaRepo.save(r);
            return true;
        }

        else
        {
            throw new UnAuthorizedException("Richiesta già somministrata, attendere l'approvazione di un admin");
        }

    }*/


    //TODO mettere a cambiare le cose
    public boolean modifyTheRequest(Utente u, AcceptOrRejectRequest request) {

        Optional<Richiesta> optionalRichiesta = richiestaRepo.findByIdrichiesta(request.getIdrichiesta());


        if (optionalRichiesta.isEmpty()) {throw new NotFoundException("Messaggio richiesta non trovato");}

        Richiesta r = optionalRichiesta.get();
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
        richiestaRepo.save(r);

        return true;
    }


}
