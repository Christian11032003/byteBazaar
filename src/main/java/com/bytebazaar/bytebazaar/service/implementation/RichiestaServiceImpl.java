package com.bytebazaar.bytebazaar.service.implementation;

import com.bytebazaar.bytebazaar.dto.request.ChangeRequestAcceptRequest;
import com.bytebazaar.bytebazaar.dto.request.LoginRequest;
import com.bytebazaar.bytebazaar.model.Richiesta;
import com.bytebazaar.bytebazaar.model.Ruolo;
import com.bytebazaar.bytebazaar.model.Stato;
import com.bytebazaar.bytebazaar.model.Utente;
import com.bytebazaar.bytebazaar.repository.RichiestaRepository;
import com.bytebazaar.bytebazaar.repository.UtenteRepository;
import com.bytebazaar.bytebazaar.service.definition.RichiestaService;
import com.bytebazaar.bytebazaar.utils.Util;
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
        if(r.getUtente().getIdutente() == u.getIdutente())
        {
            r = richiestaRepo.save(r);
            return true;
        }

        return false;
    }

    public boolean richiesta(LoginRequest request)
    {
        Optional<Utente> utenteOptional = utenteRepo.findByUsernameAndPassword(request.getUsername(), request.getPassword());

        if (Util.roleControlCustomer(request.getUsername(), request.getPassword(), Ruolo.CLIENTE) && utenteOptional.isPresent())
        {
            Utente u = utenteOptional.get();

            Optional<Richiesta> richiestaOptional = richiestaRepo.findByUtente_UsernameAndUtente_Password(request.getUsername(), request.getPassword());

            if (richiestaOptional.isEmpty())
            {
                Richiesta r = new Richiesta();
                r.setUtente(u);
                r.setStato(Stato.RICHIESTA);
                r = richiestaRepo.save(r);
                return true;
            }
        }


        return false;




    }


    public boolean changeRequestAcceptInRegistration(ChangeRequestAcceptRequest request) {

        Optional<Richiesta> optionalRichiesta = richiestaRepo.findByIdrichiesta(request.getIdrichiesta());

        if (optionalRichiesta.isEmpty()) {
            // La richiesta non esiste
            return false;
        }

        Richiesta r = optionalRichiesta.get();

        if (!Util.roleControlAdmin(request.getUsernameAdmin(), request.getPasswordAdmin(), Ruolo.ADMIN)) {
            // L'amministratore non ha le credenziali corrette
            return false;
        }

        Utente u = r.getUtente();

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
        } else {
            // Stato non valido
            return false;
        }

        // Aggiorna l'utente e la richiesta nel repository
        u = utenteRepo.save(u);
        r = richiestaRepo.save(r);

        return true;
    }


}
