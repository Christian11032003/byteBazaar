package com.bytebazaar.bytebazaar.service.implementation;

import com.bytebazaar.bytebazaar.dto.request.ChangeRequestAcceptRequest;
import com.bytebazaar.bytebazaar.exception.messaggiException.NotFoundException;
import com.bytebazaar.bytebazaar.exception.messaggiException.UnAuthorizedException;
import com.bytebazaar.bytebazaar.model.Richiesta;
import com.bytebazaar.bytebazaar.model.Ruolo;
import com.bytebazaar.bytebazaar.model.Stato;
import com.bytebazaar.bytebazaar.model.Utente;
import com.bytebazaar.bytebazaar.repository.RichiestaRepository;
import com.bytebazaar.bytebazaar.repository.UtenteRepository;
import com.bytebazaar.bytebazaar.security.TokenUtil;
import com.bytebazaar.bytebazaar.service.definition.RichiestaService;
import com.bytebazaar.bytebazaar.utils.Util;
import lombok.SneakyThrows;
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


    public boolean richiesta(Utente u)
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

    }



    public boolean changeRequestAcceptInRegistration(Utente u,ChangeRequestAcceptRequest request) {

        Optional<Richiesta> optionalRichiesta = richiestaRepo.findByIdrichiesta(request.getIdrichiesta());

        System.out.println(u.getIdutente());

        if (optionalRichiesta.isEmpty()) {
            throw new NotFoundException("Messaggio richiesta non trovato");
        }

        Richiesta r = optionalRichiesta.get();

        if (u.getRuolo() != Ruolo.ADMIN) {
            throw new UnAuthorizedException("Non autorizzato");
        }

        if (request.getStato().equals(Stato.ACCETTATO)) {
            r.setStato(Stato.ACCETTATO);

            if (u.getRuolo() == Ruolo.CLIENTE) {
                u.setRuolo(Ruolo.CLIENTEVENDITORE);
            }

        } else if (request.getStato().equals(Stato.RIFIUTATO)) {
            r.setStato(Stato.RIFIUTATO);

            if (u.getRuolo() == Ruolo.CLIENTEVENDITORE) {
                u.setRuolo(Ruolo.CLIENTE);
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
