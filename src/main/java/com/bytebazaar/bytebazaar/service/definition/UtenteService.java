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
    public boolean registrationUtente(RegistrationUtenteRequest request);

    public boolean bannedOrUnBannedAdminRequest(BannedOrUnBannedAdminRequest request);

    public Utente login(LoginRequest request);

    public List<Utente> findAllClientiVenditori();
    public List<Utente> findAllVenditori();
    public List<Utente> findAllClienti();

}
