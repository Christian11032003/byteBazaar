package com.bytebazaar.bytebazaar.service.definition;

import com.bytebazaar.bytebazaar.dto.request.BannedOrUnBannedAdminRequest;
import com.bytebazaar.bytebazaar.dto.request.RegistrationUtenteRequest;


public interface UtenteService
{
    public boolean registrationUtente(RegistrationUtenteRequest request);

    public boolean bannedOrUnBannedAdminRequest(BannedOrUnBannedAdminRequest request);

}
