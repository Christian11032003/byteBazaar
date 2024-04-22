package com.bytebazaar.bytebazaar.service.implementation;

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
import com.bytebazaar.bytebazaar.repository.UtenteRepository;
import com.bytebazaar.bytebazaar.security.TokenUtil;
import com.bytebazaar.bytebazaar.service.definition.RichiestaService;
import com.bytebazaar.bytebazaar.service.definition.UtenteService;
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
    private ProdottoRepository prodottoRepo;

    @Autowired
    private RichiestaService serviceRichiesta;

    @Autowired
    private TokenUtil tokenUtil;


    //funzionalità superAdmin
    public boolean bannedOrUnBannedAdmin(BannedOrUnBannedRequest request){

        Optional<Utente> ut = utenteRepo.findById(request.getIdutente());

        if (ut.isPresent())
        {
            Utente utente = ut.get();

            if(utente.getRuolo() == Ruolo.ADMIN)
            {
                utente.setBloccato(!utente.isBloccato());
            }

            else {
                throw new NotFoundException("Utente ADMIN non trovato");
            }


            utenteRepo.save(utente);
            return true;
        }

        else
        {
            throw new NotFoundException("Utente non trovato");
        }

    }

    //funzionalità dell'admin
    @Override
    public boolean bannedOrUnBannedUser(BannedOrUnBannedRequest request) {

        Optional<Utente> ut = utenteRepo.findById(request.getIdutente());

        if (ut.isPresent())
        {
            Utente utente = ut.get();

            if(utente.getRuolo() == Ruolo.CLIENTE || utente.getRuolo() == Ruolo.VENDITORE)
            {
                utente.setBloccato(!utente.isBloccato());
            }

            else {
                throw new NotFoundException("Utente ADMIN non trovato");
            }


            utenteRepo.save(utente);
            return true;
        }

        else
        {
            throw new NotFoundException("Utente non trovato");
        }
    }


    public List<Utente> findAllClienti() {return utenteRepo.findAllByRuolo(Ruolo.CLIENTE);}


    public List<Utente> findAllVenditori() {return utenteRepo.findAllByRuolo(Ruolo.VENDITORE);}


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
            u = utenteRepo.save(u);

            if ((request.getRuolo() == Ruolo.VENDITORE) || (request.getRuolo() == Ruolo.CLIENTE)) {
                return serviceRichiesta.registrazioneRichiesta(u);
            } else {
                return true;
            }

        }

        return false;
    }

    @Override
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
                utenteRepo.save(u);
                return true;
            }

            ;



        }

        throw new BadRequestException("Puoi solo registrare un utente con ruolo admin ");

    }


    public Utente login(LoginRequest request){

        Optional<Utente> ut=utenteRepo.findByUsernameAndPassword(request.getUsername(),request.getPassword());
        Utente u = ut.orElseThrow(()->new UnAuthorizedException("Utente inesistente nel database"));
        String token= tokenUtil.generaToken(u);
        u.setToken(token);
        utenteRepo.save(u);
        return u;
    }

    public boolean logout(Utente u)
    {
        u.setToken(null);
        utenteRepo.save(u);
        return true;
    }









}
