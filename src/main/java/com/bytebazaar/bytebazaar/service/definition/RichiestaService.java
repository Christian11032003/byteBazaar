package com.bytebazaar.bytebazaar.service.definition;

import com.bytebazaar.bytebazaar.dto.request.ChangeRequestAcceptRequest;
import com.bytebazaar.bytebazaar.dto.request.LoginRequest;
import com.bytebazaar.bytebazaar.exception.messaggiException.exceptionRichiesta.MessaggioRichiestaAlreadyDefined;
import com.bytebazaar.bytebazaar.exception.messaggiException.exceptionUtente.MessaggioUtenteNotFoundException;
import com.bytebazaar.bytebazaar.exception.messaggiException.exceptionUtente.MessaggioUtenteUnauthorizedException;
import com.bytebazaar.bytebazaar.model.Utente;



public interface RichiestaService
{
    public boolean registrazioneRichiesta(Utente u);

    public boolean richiesta(LoginRequest request) throws MessaggioRichiestaAlreadyDefined, MessaggioUtenteUnauthorizedException;

    public boolean changeRequestAcceptInRegistration(ChangeRequestAcceptRequest request);

}
