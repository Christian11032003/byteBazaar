package com.bytebazaar.bytebazaar.service.definition;

import com.bytebazaar.bytebazaar.dto.request.AddProductToCartRequest;
import com.bytebazaar.bytebazaar.dto.request.DeleteObjectFromCartRequest;
import com.bytebazaar.bytebazaar.dto.request.SubtractQuantityRequest;
import com.bytebazaar.bytebazaar.model.Carrello;
import com.bytebazaar.bytebazaar.model.Utente;

public interface OggettocarrelloService
{
    public boolean aggiungiAlCarrello(Utente u, AddProductToCartRequest request);

    public boolean modificaQuantitaRimanenti(Utente u, Carrello c);

    public boolean sottraiQuantita(Utente u, SubtractQuantityRequest request);

    public boolean eliminaoggettocarrello(Utente u, DeleteObjectFromCartRequest request);
}
