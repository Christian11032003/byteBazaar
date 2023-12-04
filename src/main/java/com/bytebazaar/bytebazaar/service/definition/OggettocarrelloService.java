package com.bytebazaar.bytebazaar.service.definition;

import com.bytebazaar.bytebazaar.dto.request.AggiungiProdottoInCarrelloRequest;
import com.bytebazaar.bytebazaar.model.Oggettocarrello;

public interface OggettocarrelloService
{
    public boolean aggiungiAlCarrello(AggiungiProdottoInCarrelloRequest request);
}
