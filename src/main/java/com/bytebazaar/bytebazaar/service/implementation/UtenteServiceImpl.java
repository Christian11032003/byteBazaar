package com.bytebazaar.bytebazaar.service.implementation;

import com.bytebazaar.bytebazaar.dto.request.BannedOrUnBannedAdminRequest;
import com.bytebazaar.bytebazaar.dto.request.LoginRequest;
import com.bytebazaar.bytebazaar.dto.request.RegistrationUtenteRequest;
import com.bytebazaar.bytebazaar.exception.messaggiException.exceptionUtente.MessaggioUtenteNotFoundException;
import com.bytebazaar.bytebazaar.exception.messaggiException.exceptionUtente.MessaggioUtenteUnauthorizedException;
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



    public boolean bannedOrUnBannedAdminRequest(BannedOrUnBannedAdminRequest request) throws MessaggioUtenteUnauthorizedException, MessaggioUtenteNotFoundException {
        if (request == null || utenteRepo.findByIdutente(request.getIdutente()).isEmpty() || !Util.roleControlAdmin(request.getUsernameAdmin(), request.getPasswordAdmin(), Ruolo.ADMIN)) {
            throw new MessaggioUtenteUnauthorizedException("Non Autorizzato");
        }

        Optional<Utente> ut = utenteRepo.findById(request.getIdutente());

        if (ut.isPresent()) {
            Utente utente = ut.get();
            utente.setBloccato(!utente.isBloccato());

            utenteRepo.save(utente);
            return true;
        }
        else
        {
            throw new MessaggioUtenteNotFoundException("Utente non trovato");
        }

    }



    public List<Utente> findAllClienti(LoginRequest request) throws MessaggioUtenteUnauthorizedException
    {
        if(Util.roleControlAdmin(request.getUsername(),request.getPassword(),Ruolo.ADMIN))
        {
            return utenteRepo.findAllByRuolo(Ruolo.CLIENTE);
        }

        else
        {
            throw new MessaggioUtenteUnauthorizedException("Non autorizzato");
        }



    }


    public List<Utente> findAllVenditori(LoginRequest request) throws MessaggioUtenteUnauthorizedException
    {
        if(Util.roleControlAdmin(request.getUsername(),request.getPassword(),Ruolo.ADMIN)) {
            return utenteRepo.findAllByRuolo(Ruolo.VENDITORE);
        }

        else
        {
            throw new MessaggioUtenteUnauthorizedException("Non autorizzato");
        }
    }


    public List<Utente> findAllClientiVenditori(LoginRequest request) throws MessaggioUtenteUnauthorizedException
    {
        if(Util.roleControlAdmin(request.getUsername(),request.getPassword(),Ruolo.ADMIN)) {
            return utenteRepo.findAllByRuolo(Ruolo.CLIENTEVENDITORE);
        }

        else
        {
            throw new MessaggioUtenteUnauthorizedException("Non autorizzato");
        }
    }

    public boolean registrationUtente(RegistrationUtenteRequest request) {

        if ((request.getPassword().equals(request.getPasswordRipetuta())))
        {

            Utente u = new Utente();
            u.setNome(request.getNome());
            u.setCognome(request.getCognome());
            u.setEmail(request.getEmail());
            u.setUsername(request.getUsername());
            u.setPassword(request.getPassword());
            u.setRuolo(request.getRuolo());
            u = utenteRepo.save(u);

            if((request.getRuolo() == Ruolo.VENDITORE) || (request.getRuolo() == Ruolo.CLIENTEVENDITORE))
            {
                return serviceRichiesta.registrazioneRichiesta(u);
            }

            else
            {
                return true;
            }

        }

        return false;

    }

    public Utente login(LoginRequest request) throws MessaggioUtenteNotFoundException {

        Optional<Utente> ut=utenteRepo.findByUsernameAndPassword(request.getUsername(),request.getPassword()) ;
        if(ut.isEmpty())
        {
            throw new MessaggioUtenteNotFoundException("Utente inesistente nel database");
        }
        return ut.orElse(null);
    }




}
