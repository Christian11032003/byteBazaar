package com.bytebazaar.bytebazaar.facade;

import com.bytebazaar.bytebazaar.dto.request.BannedOrUnBannedRequest;
import com.bytebazaar.bytebazaar.dto.request.LoginRequest;
import com.bytebazaar.bytebazaar.dto.request.RegistrationUserRequest;
import com.bytebazaar.bytebazaar.exception.messaggiException.BadRequestException;
import com.bytebazaar.bytebazaar.exception.messaggiException.NotFoundException;
import com.bytebazaar.bytebazaar.exception.messaggiException.UnAuthorizedException;
import com.bytebazaar.bytebazaar.model.Prodotto;
import com.bytebazaar.bytebazaar.model.Ruolo;
import com.bytebazaar.bytebazaar.model.Utente;
import com.bytebazaar.bytebazaar.repository.ProdottoRepository;
import com.bytebazaar.bytebazaar.security.TokenUtil;
import com.bytebazaar.bytebazaar.service.definition.RichiestaService;
import com.bytebazaar.bytebazaar.service.definition.UtenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UtenteFacade
{

    private final UtenteService serviceUtente;


    private final ProdottoRepository prodottoRepo;


    private final RichiestaService serviceRichiesta;


    private final TokenUtil tokenUtil;



    public boolean bannedOrUnBannedAdmin(BannedOrUnBannedRequest request){

        Utente u = serviceUtente.getById(request.getIdutente());


        if(u.getRuolo() == Ruolo.ADMIN)
        {
            u.setBloccato(!u.isBloccato());
        }

        else
        {
            throw new NotFoundException("Utente ADMIN non trovato");
        }


        serviceUtente.salva(u);
        return true;



    }

    //funzionalità dell'admin

    public boolean bannedOrUnBannedUser(BannedOrUnBannedRequest request) {

        Utente u = serviceUtente.getById(request.getIdutente());



        if(u.getRuolo() == Ruolo.CLIENTE || u.getRuolo() == Ruolo.VENDITORE)
        {
            u.setBloccato(!u.isBloccato());
        }

        else
        {
            throw new NotFoundException("Utente ADMIN non trovato");
        }


        serviceUtente.salva(u);
        return true;

    }


    public List<Utente> findAllClienti() {return serviceUtente.findAllByRuolo(Ruolo.CLIENTE);}


    public List<Utente> findAllVenditori() {return serviceUtente.findAllByRuolo(Ruolo.VENDITORE);}


    //funzionalità del cliente e del venditore
    public List<Prodotto> findAllOtherProducts(LoginRequest request) {return prodottoRepo.findAllByUtente_UsernameIsNot(request.getUsername());}


    //funzionalità venditore
    public List<Prodotto> findAllHisProducts(LoginRequest request) {return prodottoRepo.findAllByUtente_UsernameAndUtente_Password(request.getUsername(), request.getPassword());}

    //funzionalità di tutti

    public boolean registrationUtente(RegistrationUserRequest request) {

        if ((request.getPassword().equals(request.getPasswordRipetuta()))) {

            Utente u = new Utente();
            u.setNome(request.getNome());
            u.setCognome(request.getCognome());
            u.setEmail(request.getEmail());
            u.setUsername(request.getUsername());
            u.setPassword(request.getPassword());
            u.setRuolo(request.getRuolo());
            serviceUtente.salva(u);

            if ((request.getRuolo() == Ruolo.VENDITORE) || (request.getRuolo() == Ruolo.CLIENTE)) {
                return serviceRichiesta.registrazioneRichiesta(u);
            } else {
                return true;
            }

        }

        return false;
    }


    public boolean registrationAdmin(RegistrationUserRequest request) {
        if ((request.getPassword().equals(request.getPasswordRipetuta()))) {

            Utente u = new Utente();
            u.setNome(request.getNome());
            u.setCognome(request.getCognome());
            u.setEmail(request.getEmail());
            u.setUsername(request.getUsername());
            u.setPassword(request.getPassword());
            u.setRuolo(request.getRuolo());
            if(request.getRuolo() == Ruolo.ADMIN)
            {
                serviceUtente.salva(u);
                return true;
            }

        }

        throw new BadRequestException("Puoi solo registrare un utente con ruolo admin ");

    }


    public Utente login(LoginRequest request){

        Utente u = serviceUtente.getByUsername(request.getUsername());

        String token= tokenUtil.generaToken(u);
        u.setToken(token);
        serviceUtente.salva(u);
        return u;
    }

    public boolean logout(Utente u)
    {
        u.setToken(null);
        serviceUtente.salva(u);
        return true;
    }







}
