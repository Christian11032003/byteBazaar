package com.bytebazaar.bytebazaar.service.definition;

import com.bytebazaar.bytebazaar.dto.request.MandaMessaggioRequest;

public interface MessaggioService
{
    public boolean mandaMessaggio(MandaMessaggioRequest request);
}
