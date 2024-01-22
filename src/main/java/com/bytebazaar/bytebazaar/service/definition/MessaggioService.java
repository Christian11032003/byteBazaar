package com.bytebazaar.bytebazaar.service.definition;

import com.bytebazaar.bytebazaar.dto.request.SendMessageRequest;
import com.bytebazaar.bytebazaar.model.Utente;

public interface MessaggioService
{
    public boolean mandaMessaggio(Utente u, SendMessageRequest request);
}
