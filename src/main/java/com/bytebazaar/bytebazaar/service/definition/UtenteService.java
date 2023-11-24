package com.bytebazaar.bytebazaar.service.definition;

import com.bytebazaar.bytebazaar.dto.request.RegistrationUtenteRequest;
import org.springframework.stereotype.Service;


public interface UtenteService
{
    public boolean registrazioneUtente(RegistrationUtenteRequest request);
}
