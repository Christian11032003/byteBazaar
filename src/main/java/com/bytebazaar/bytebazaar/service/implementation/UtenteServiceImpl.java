package com.bytebazaar.bytebazaar.service.implementation;

import com.bytebazaar.bytebazaar.dto.request.BannedOrUnBannedAdminRequest;
import com.bytebazaar.bytebazaar.dto.request.LoginRequest;
import com.bytebazaar.bytebazaar.dto.request.RegistrationUtenteRequest;
import com.bytebazaar.bytebazaar.exception.messaggiException.BadRequestException;
import com.bytebazaar.bytebazaar.exception.messaggiException.UnAuthorizedException;
import com.bytebazaar.bytebazaar.model.Prodotto;
import com.bytebazaar.bytebazaar.model.Ruolo;
import com.bytebazaar.bytebazaar.model.Utente;
import com.bytebazaar.bytebazaar.repository.ProdottoRepository;
import com.bytebazaar.bytebazaar.repository.UtenteRepository;
import com.bytebazaar.bytebazaar.service.definition.RichiestaService;
import com.bytebazaar.bytebazaar.service.definition.UtenteService;
import com.bytebazaar.bytebazaar.utils.Util;
import lombok.SneakyThrows;
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


    //funzionalità admin
    @SneakyThrows
    public boolean bannedOrUnBannedAdminRequest(BannedOrUnBannedAdminRequest request){
        if (request == null || utenteRepo.findByIdutente(request.getIdutente()).isEmpty() || !Util.roleControl(request.getUsernameAdmin(), request.getPasswordAdmin(), Ruolo.ADMIN)) {
            throw new UnAuthorizedException("Non Autorizzato");
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
            throw new UnAuthorizedException("Utente non trovato");
        }

    }


    @SneakyThrows
    public List<Utente> findAllClienti(LoginRequest request)
    {

        if(Util.roleControl(request.getUsername(),request.getPassword(),Ruolo.ADMIN)) {
            return utenteRepo.findAllByRuolo(Ruolo.CLIENTE);
        }

        else
        {
            throw new UnAuthorizedException("Non autorizzato");
        }

    }

    @SneakyThrows
    public List<Utente> findAllVenditori(LoginRequest request)
    {

        if(Util.roleControl(request.getUsername(),request.getPassword(),Ruolo.ADMIN)) {
            return utenteRepo.findAllByRuolo(Ruolo.VENDITORE);
        }

        else
        {
            throw new UnAuthorizedException("Non autorizzato");
        }
    }

    @SneakyThrows
    public List<Utente> findAllClientiVenditori(LoginRequest request)
    {
        if(Util.roleControl(request.getUsername(),request.getPassword(),Ruolo.ADMIN)) {
            return utenteRepo.findAllByRuolo(Ruolo.CLIENTEVENDITORE);
        }

        else
        {
            throw new UnAuthorizedException("Non autorizzato");
        }
    }

    //funzionalità venditore

    @SneakyThrows
    public List<Prodotto> findAllHisProducts(LoginRequest request)
    {
        if(Util.roleControl(request.getUsername(),request.getPassword(),Ruolo.VENDITORE))
        {
            return prodottoRepo.findAllByUtente_UsernameAndUtente_Password(request.getUsername(), request.getPassword());
        }

        else {
            throw new UnAuthorizedException("Non autorizzato");
        }
    }

    //funzionalità del cliente
    @SneakyThrows
    public List<Prodotto> findAllOtherProducts(LoginRequest request)
    {
        if(Util.roleControl(request.getUsername(),request.getPassword(),Ruolo.CLIENTE))
        {
            return prodottoRepo.findAllByUtente_UsernameIsNot(request.getUsername());
        }

        else {
            throw new UnAuthorizedException("Non autorizzato");
        }
    }


    //funzionalità di tutti
    @SneakyThrows
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

        throw new BadRequestException("Impossibile completare la registrazione");

    }
    @SneakyThrows
    public Utente login(LoginRequest request){

        Optional<Utente> ut=utenteRepo.findByUsernameAndPassword(request.getUsername(),request.getPassword()) ;
        if(ut.isEmpty())
        {
            throw new UnAuthorizedException("Utente inesistente nel database");
        }
        return ut.orElse(null);
    }




}
