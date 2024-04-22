package com.bytebazaar.bytebazaar.service.definition;

import com.bytebazaar.bytebazaar.dto.request.BannedOrUnBannedRequest;
import com.bytebazaar.bytebazaar.dto.request.LoginRequest;
import com.bytebazaar.bytebazaar.dto.request.RegistrationUserRequest;
import com.bytebazaar.bytebazaar.model.Prodotto;
import com.bytebazaar.bytebazaar.model.Utente;

import java.util.List;


public interface UtenteService
{
    //funzionalità dell'superAdmin
    public boolean bannedOrUnBannedAdmin(BannedOrUnBannedRequest request);

    //funzionalità dell'admin
    public List<Utente> findAllVenditori();
    public List<Utente> findAllClienti();
    public boolean bannedOrUnBannedUser(BannedOrUnBannedRequest request);

    //funzionalità del venditore
    public List<Prodotto> findAllHisProducts(LoginRequest request);

    //funzionalità del cliente e venditore
    public List<Prodotto> findAllOtherProducts(LoginRequest request);


    //funzionalità di tutti
    public boolean registrationUtente(RegistrationUserRequest request);
    public boolean registrationAdmin(RegistrationUserRequest request);
    public Utente login(LoginRequest request);
    public boolean logout(Utente u);


}
