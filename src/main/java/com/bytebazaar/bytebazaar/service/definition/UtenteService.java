package com.bytebazaar.bytebazaar.service.definition;

import com.bytebazaar.bytebazaar.dto.request.BannedOrUnBannedAdminRequest;
import com.bytebazaar.bytebazaar.dto.request.LoginRequest;
import com.bytebazaar.bytebazaar.dto.request.RegistrationUtenteRequest;
import com.bytebazaar.bytebazaar.exception.exceptionUtente.MessaggioUtenteNotFoundException;
import com.bytebazaar.bytebazaar.exception.exceptionUtente.MessaggioUtenteUnauthorizedException;
import com.bytebazaar.bytebazaar.model.Utente;

import java.util.List;


public interface UtenteService
{
    //funzionalità di dell'admin
    public boolean bannedOrUnBannedAdminRequest(BannedOrUnBannedAdminRequest request) throws MessaggioUtenteUnauthorizedException, MessaggioUtenteNotFoundException;
    public List<Utente> findAllClientiVenditori(LoginRequest request) throws MessaggioUtenteUnauthorizedException;
    public List<Utente> findAllVenditori(LoginRequest request) throws MessaggioUtenteUnauthorizedException ;
    public List<Utente> findAllClienti(LoginRequest request) throws MessaggioUtenteUnauthorizedException;


    //funzionalità di tutti
    public boolean registrationUtente(RegistrationUtenteRequest request) throws MessaggioUtenteUnauthorizedException;
    public Utente login(LoginRequest request) throws MessaggioUtenteNotFoundException;

}
