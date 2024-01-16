package com.bytebazaar.bytebazaar.service.definition;

import com.bytebazaar.bytebazaar.dto.request.AggiungiProdottoInCarrelloRequest;
import com.bytebazaar.bytebazaar.dto.request.EliminaOggettoCarrelloRequest;
import com.bytebazaar.bytebazaar.dto.request.LoginRequest;
import com.bytebazaar.bytebazaar.dto.request.SottraiQuantitaRequest;
import com.bytebazaar.bytebazaar.exception.messaggiException.BadRequestException;
import com.bytebazaar.bytebazaar.exception.messaggiException.NotFoundException;
import com.bytebazaar.bytebazaar.exception.messaggiException.UnAuthorizedException;
import com.bytebazaar.bytebazaar.model.Carrello;
import com.bytebazaar.bytebazaar.model.Oggettocarrello;
import com.bytebazaar.bytebazaar.model.Utente;

public interface OggettocarrelloService
{
    public boolean aggiungiAlCarrello(Utente u,AggiungiProdottoInCarrelloRequest request);

    public boolean modificaQuantitaRimanenti(Utente u, Carrello c);

    public boolean sottraiQuantita(Utente u, SottraiQuantitaRequest request);

    public boolean eliminaoggettocarrello(Utente u,EliminaOggettoCarrelloRequest request);
}
