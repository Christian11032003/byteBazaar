package com.bytebazaar.bytebazaar.service.definition;

import com.bytebazaar.bytebazaar.dto.request.MandaMessaggioRequest;
import com.bytebazaar.bytebazaar.exception.messaggiException.NotFoundException;
import com.bytebazaar.bytebazaar.model.Utente;

public interface MessaggioService
{
    public boolean mandaMessaggio(Utente u, MandaMessaggioRequest request);
}
