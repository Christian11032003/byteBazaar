package com.bytebazaar.bytebazaar.service.definition;

import com.bytebazaar.bytebazaar.dto.request.MandaMessaggioRequest;
import com.bytebazaar.bytebazaar.exception.messaggiException.NotFoundException;

public interface MessaggioService
{
    public boolean mandaMessaggio(MandaMessaggioRequest request) throws NotFoundException;
}
