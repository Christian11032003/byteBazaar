package com.bytebazaar.bytebazaar.service.implementation;

import com.bytebazaar.bytebazaar.dto.request.BannedOrUnBannedAdminRequest;
import com.bytebazaar.bytebazaar.dto.request.LoginRequest;
import com.bytebazaar.bytebazaar.dto.request.RegistrationUtenteRequest;
import com.bytebazaar.bytebazaar.model.Ruolo;
import com.bytebazaar.bytebazaar.model.Utente;
import com.bytebazaar.bytebazaar.repository.UtenteRepository;
import com.bytebazaar.bytebazaar.service.definition.RichiestaService;
import com.bytebazaar.bytebazaar.service.definition.UtenteService;
import com.bytebazaar.bytebazaar.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtenteServiceImpl implements UtenteService
{

    @Autowired
    private UtenteRepository utenteRepo;

    @Autowired
    private RichiestaService serviceRichiesta;



    public boolean bannedOrUnBannedAdminRequest(BannedOrUnBannedAdminRequest request)
    {
        if (request == null || utenteRepo.findByIdutente(request.getIdutente()).isEmpty() || !Util.roleControlAdmin(request.getUsernameAdmin(), request.getPasswordAdmin(), Ruolo.ADMIN)) {
            return false;
        }

        Optional<Utente> ut = utenteRepo.findById(request.getIdutente());

        if (ut.isPresent()) {
            Utente utente = ut.get();
            utente.setBloccato(!utente.isBloccato());

            utenteRepo.save(utente);
            return true;
        } else {
            return false;
        }

    }


    @Override
    public List<Utente> findAllClienti(LoginRequest request)
    {
        if(Util.roleControlAdmin(request.getUsername(),request.getPassword(),Ruolo.ADMIN))
        {
            return utenteRepo.findAllByRuolo(Ruolo.CLIENTE);
        }

        return null;

    }

    @Override
    public List<Utente> findAllVenditori(LoginRequest request)
    {
        if(Util.roleControlAdmin(request.getUsername(),request.getPassword(),Ruolo.ADMIN)) {
            return utenteRepo.findAllByRuolo(Ruolo.VENDITORE);
        }

        return null;
    }

    @Override
    public List<Utente> findAllClientiVenditori(LoginRequest request)
    {
        if(Util.roleControlAdmin(request.getUsername(),request.getPassword(),Ruolo.ADMIN)) {
            return utenteRepo.findAllByRuolo(Ruolo.CLIENTEVENDITORE);
        }

        return null;
    }

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

    @Override
    public Utente login(LoginRequest request) {

        Optional<Utente> ut=utenteRepo.findByUsernameAndPassword(request.getUsername(),request.getPassword());
        if(ut.isEmpty())
        {
            //throw new UtenteNonTrovatoException("nessun utente attivo è stato trovato con questa username o password");
        }
        if(ut.isPresent())
            return ut.get();
        else return null;
    }




}
