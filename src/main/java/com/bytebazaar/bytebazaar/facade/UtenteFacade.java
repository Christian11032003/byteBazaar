package com.bytebazaar.bytebazaar.facade;

import com.bytebazaar.bytebazaar.dto.request.*;
import com.bytebazaar.bytebazaar.exception.messaggiException.BadRequestException;
import com.bytebazaar.bytebazaar.exception.messaggiException.NotFoundException;
import com.bytebazaar.bytebazaar.model.*;
import com.bytebazaar.bytebazaar.repository.FeedbackRepository;
import com.bytebazaar.bytebazaar.repository.MessaggioRepository;
import com.bytebazaar.bytebazaar.repository.ProdottoRepository;
import com.bytebazaar.bytebazaar.security.TokenUtil;
import com.bytebazaar.bytebazaar.service.definition.UtenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UtenteFacade
{

    private final UtenteService serviceUtente;

    private final ProdottoRepository prodottoRepo;

    private final MessaggioRepository messaggioRepo;

    private final FeedbackRepository feedbackRepo;

    private final RichiestaFacade richiestaFacade;

    private final TokenUtil tokenUtil;



    public boolean bannedOrUnBannedAdmin(BannedOrUnBannedRequestDTO request){

        Utente u = serviceUtente.getById(request.getIdUtente());


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

    public boolean bannedOrUnBannedUser(BannedOrUnBannedRequestDTO request) {

        Utente u = serviceUtente.getById(request.getIdUtente());



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

    public List<Utente> findAllAdmin(){return serviceUtente.findAllByRuolo(Ruolo.ADMIN);}

    //funzionalità admin
    public List<Utente> findAllClienti() {return serviceUtente.findAllByRuolo(Ruolo.CLIENTE);}

    public List<Utente> findAllVenditori() {return serviceUtente.findAllByRuolo(Ruolo.VENDITORE);}

    public List<Prodotto> findAllProdottiUser(FindThingsRequestDTO request)
    {
        return prodottoRepo.trovaProdottiUser(request.getIdUtente());
    }

    public List<Prodotto> findAllProdottiUserInKart(FindThingsRequestDTO request)
    {
        return prodottoRepo.getAllProductInKart(request.getIdUtente());
    }

    public List<Messaggio> findAllMessaggeUser(FindThingsRequestDTO request)
    {
        return messaggioRepo.findAllByUtente_Id(request.getIdUtente());
    }

    public List<Feedback> findAllFeedbackUser(FindThingsRequestDTO request)
    {
        return feedbackRepo.findAllByOggettocarrello_Carrello_Utente_Id(request.getIdUtente());
    }




    //funzionalità del cliente e del venditore

    public List<Messaggio> findMyOwnMessage(Utente u){return messaggioRepo.findAllByUtente(u);}
    public List<Prodotto> findAllOtherProducts(Utente u, FilterProductRequestDTO request)
    {
        return prodottoRepo.findAllByUtenteIsNotAndCondizione(u,request.getCondizione());
    }
    public List<Feedback> findMyOwnFeedback(Utente u) {return feedbackRepo.findAllByOggettocarrello_Carrello_Utente(u);}


    //funzionalità venditore
    public List<Prodotto> findAllHisProducts(Utente u) {return prodottoRepo.trovaProdottiUser(u.getId());}

    //funzionalità di tutti

    public boolean registrationUtente(RegistrationUserRequestDTO request) {

        if ((request.getPassword().equals(request.getPasswordRipetuta()))) {

            Utente u = new Utente();
            u.setNome(request.getNome());
            u.setCognome(request.getCognome());
            u.setEmail(request.getEmail());
            u.setUsername(request.getUsername());
            u.setPassword(request.getPassword());
            u.setRuolo(request.getRuolo());
            serviceUtente.salva(u);

            if (request.getRuolo() == Ruolo.VENDITORE) {
                return richiestaFacade.registrazioneRichiesta(u);
            } else {
                return true;
            }

        }

        return false;
    }


    public boolean registrationAdmin(RegistrationUserRequestDTO request) {
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


    public Utente login(LoginRequestDTO request){

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
