package com.bytebazaar.bytebazaar.service.definition;

import com.bytebazaar.bytebazaar.dto.request.BannedOrUnBannedAdminRequest;
import com.bytebazaar.bytebazaar.dto.request.LoginRequest;
import com.bytebazaar.bytebazaar.dto.request.RegistrationUserRequest;
import com.bytebazaar.bytebazaar.model.Prodotto;
import com.bytebazaar.bytebazaar.model.Utente;

import java.util.List;


public interface UtenteService
{
    //funzionalità di dell'admin
    public boolean bannedOrUnBannedAdminRequest(BannedOrUnBannedAdminRequest request);
    public List<Utente> findAllClientiVenditori();
    public List<Utente> findAllVenditori();
    public List<Utente> findAllClienti();

    //funzionalità del venditore
    public List<Prodotto> findAllHisProducts(LoginRequest request);

    //funzionalità del cliente
    public List<Prodotto> findAllOtherProducts(LoginRequest request);

    //funzionalità


    //funzionalità di tutti
    public boolean registrationUtente(RegistrationUserRequest request);
    public Utente login(LoginRequest request);

    public boolean logout(Utente u);

    public Utente trovaPerUsername(String username);

}
