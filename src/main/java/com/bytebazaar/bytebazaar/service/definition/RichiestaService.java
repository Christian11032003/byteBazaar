package com.bytebazaar.bytebazaar.service.definition;

import com.bytebazaar.bytebazaar.dto.request.AcceptOrRejectRequest;
import com.bytebazaar.bytebazaar.model.Utente;


public interface RichiestaService
{
    public boolean registrazioneRichiesta(Utente u);

    public boolean richiesta(Utente u);

    public boolean changeRequestAcceptInRegistration(Utente u, AcceptOrRejectRequest request);

}
