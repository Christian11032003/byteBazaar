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

public interface OggettocarrelloService
{
    public boolean aggiungiAlCarrello(AggiungiProdottoInCarrelloRequest request) throws UnAuthorizedException, NotFoundException, BadRequestException;

    public boolean modificaQuantitaRimanenti(LoginRequest request, Carrello c);

    public boolean sottraiQuantita(SottraiQuantitaRequest request) throws UnAuthorizedException, NotFoundException;

    public boolean eliminaoggettocarrello(EliminaOggettoCarrelloRequest request) throws UnAuthorizedException, NotFoundException;;
}
