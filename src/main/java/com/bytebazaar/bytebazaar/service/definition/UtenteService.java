package com.bytebazaar.bytebazaar.service.definition;

import com.bytebazaar.bytebazaar.dto.request.BannedOrUnBannedAdminRequest;
import com.bytebazaar.bytebazaar.dto.request.ChangeRequestAcceptRequest;
import com.bytebazaar.bytebazaar.dto.request.LoginRequest;
import com.bytebazaar.bytebazaar.dto.request.RegistrationUtenteRequest;
import com.bytebazaar.bytebazaar.model.Ruolo;
import com.bytebazaar.bytebazaar.model.Utente;

import java.util.List;


public interface UtenteService
{
    //funzionalità di dell'admin
    public boolean bannedOrUnBannedAdminRequest(BannedOrUnBannedAdminRequest request);
    public List<Utente> findAllClientiVenditori(LoginRequest request);
    public List<Utente> findAllVenditori(LoginRequest request);
    public List<Utente> findAllClienti(LoginRequest request);
    //funzionalità di tutti
    public boolean registrationUtente(RegistrationUtenteRequest request);
    public Utente login(LoginRequest request);

}
