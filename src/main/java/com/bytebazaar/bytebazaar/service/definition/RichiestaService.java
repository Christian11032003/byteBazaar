package com.bytebazaar.bytebazaar.service.definition;

import com.bytebazaar.bytebazaar.dto.request.ChangeRequestAcceptRequest;
import com.bytebazaar.bytebazaar.dto.request.LoginRequest;
import com.bytebazaar.bytebazaar.exception.messaggiException.AlreadyReportedException;
import com.bytebazaar.bytebazaar.exception.messaggiException.NotFoundException;
import com.bytebazaar.bytebazaar.model.Utente;
import com.bytebazaar.bytebazaar.security.TokenUtil;


public interface RichiestaService
{
    public boolean registrazioneRichiesta(Utente u);

    public boolean richiesta(TokenUtil util) throws AlreadyReportedException, NotFoundException;

    public boolean changeRequestAcceptInRegistration(ChangeRequestAcceptRequest request, TokenUtil util);

}
