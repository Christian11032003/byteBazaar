package com.bytebazaar.bytebazaar.service.implementation;

import com.bytebazaar.bytebazaar.dto.request.BannedOrUnBannedAdminRequest;
import com.bytebazaar.bytebazaar.dto.request.RegistrationUtenteRequest;
import com.bytebazaar.bytebazaar.model.Ruolo;
import com.bytebazaar.bytebazaar.model.Utente;
import com.bytebazaar.bytebazaar.repository.RichiestaRepository;
import com.bytebazaar.bytebazaar.repository.UtenteRepository;
import com.bytebazaar.bytebazaar.service.definition.RichiestaService;
import com.bytebazaar.bytebazaar.service.definition.UtenteService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtenteServiceImpl implements UtenteService
{

    @Autowired
    private UtenteRepository utenteRepo;
    @Autowired
    private RichiestaService serviceRichiesta;
    public boolean registrationUtente(RegistrationUtenteRequest request)
    {
        Utente u = new Utente();
        u.setNome(request.getNome());
        u.setCognome(request.getCognome());
        u.setEmail(request.getEmail());
        u.setUsername(request.getUsername());
        u.setPassword(request.getPassword());
        u.setRuolo(request.getRuolo());



        if ((request.getPassword().equals(request.getPasswordRipetuta()))) {
            u = utenteRepo.save(u);
            if((request.getRuolo() == Ruolo.VENDITORE) || (request.getRuolo() == Ruolo.CLIENTEVENDITORE))
            {
                serviceRichiesta.registrazioneRichiesta(u);
            }
            return true;
        }

        return false;
    }


    public boolean bannedOrUnBannedAdminRequest(BannedOrUnBannedAdminRequest request)
    {
        if (request == null || utenteRepo.findByIdutente(request.getId()).isEmpty() ||!roleControl(request.getUsername(), request.getPassword(), Ruolo.ADMIN)) {
            return false;
        }

        Optional<Utente> ut = utenteRepo.findById(request.getId());

        if (ut.isPresent()) {
            Utente utente = ut.get();
            utente.setBloccato(!utente.isBloccato());

            utenteRepo.save(utente);
            return true;
        } else {
            return false;
        }

    }

    public boolean acceptRequest()
    {
        return true;
    }

    public boolean roleControl(String username,String password,Ruolo ruolo){
        Utente u =utenteRepo.findByUsernameAndPassword(username,password).orElse(null);
        if(u==null)return false;
        return u.getRuolo().equals(ruolo);
    }




}
