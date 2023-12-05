package com.bytebazaar.bytebazaar.service.definition;

import com.bytebazaar.bytebazaar.dto.request.AggiungiProdottoInCarrelloRequest;
import com.bytebazaar.bytebazaar.dto.request.CreaCarrelloRequest;
import com.bytebazaar.bytebazaar.dto.request.LoginRequest;
import com.bytebazaar.bytebazaar.model.Carrello;
import com.bytebazaar.bytebazaar.model.Utente;

public interface CarrelloService
{
    public boolean confermaCarrello(LoginRequest request);


}
