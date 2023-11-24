package com.bytebazaar.bytebazaar.service.definition;

import com.bytebazaar.bytebazaar.dto.request.BannedOrUnBannedAdminRequest;
import com.bytebazaar.bytebazaar.dto.request.RegistrationUtenteRequest;
import com.bytebazaar.bytebazaar.model.Ruolo;
import com.bytebazaar.bytebazaar.model.Utente;
import org.springframework.stereotype.Service;


public interface UtenteService
{
    public boolean registrazioneUtente(RegistrationUtenteRequest request);

    public Utente bannedOrUnBannedAdminRequest(BannedOrUnBannedAdminRequest request);

    public boolean roleControl(String username, String password, Ruolo ruolo);
}
