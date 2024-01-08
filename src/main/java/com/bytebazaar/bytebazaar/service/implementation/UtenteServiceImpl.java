package com.bytebazaar.bytebazaar.service.implementation;

import com.bytebazaar.bytebazaar.dto.request.BannedOrUnBannedAdminRequest;
import com.bytebazaar.bytebazaar.dto.request.LoginRequest;
import com.bytebazaar.bytebazaar.dto.request.RegistrationUtenteRequest;
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

    @Autowired
    private TokenUtil tokenUtil;


    //funzionalità admin
    @SneakyThrows
    public boolean bannedOrUnBannedAdminRequest(BannedOrUnBannedAdminRequest request){

        Optional<Utente> ut = utenteRepo.findById(request.getIdutente());

        if (ut.isPresent()) {
            Utente utente = ut.get();
            utente.setBloccato(!utente.isBloccato());

            utenteRepo.save(utente);
            return true;
        }
        else
        {
            throw new NotFoundException("Utente non trovato");
        }

    }


    @SneakyThrows
    public List<Utente> findAllClienti()
    {
        return utenteRepo.findAllByRuolo(Ruolo.CLIENTE);
    }

    @SneakyThrows
    public List<Utente> findAllVenditori()
    {

        return utenteRepo.findAllByRuolo(Ruolo.VENDITORE);

    }

    @SneakyThrows
    public List<Utente> findAllClientiVenditori()
    {
        return utenteRepo.findAllByRuolo(Ruolo.CLIENTEVENDITORE);
    }

    //funzionalità venditore
    @SneakyThrows
    public List<Prodotto> findAllHisProducts(LoginRequest request)
    {
        return prodottoRepo.findAllByUtente_UsernameAndUtente_Password(request.getUsername(), request.getPassword());
    }

    //funzionalità del cliente
    @SneakyThrows
    public List<Prodotto> findAllOtherProducts(LoginRequest request)
    {
        return prodottoRepo.findAllByUtente_UsernameIsNot(request.getUsername());

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
        Utente u = ut.orElseThrow(()->new UnAuthorizedException("Utente inesistente nel database"));
        String token= tokenUtil.generaToken(u);
        u.setToken(token);
        utenteRepo.save(u);
        return u;
    }



    public Utente trovaPerUsername(String username)
    {
        return utenteRepo.findByUsername(username).orElse(null);
    }







}
