package com.bytebazaar.bytebazaar.service.definition;


import com.bytebazaar.bytebazaar.model.OggettoCarrello;

import java.util.Optional;

public interface OggettoCarrelloService
{
    public void salva(OggettoCarrello o);

    public OggettoCarrello getByCarrelloIdcarrelloAndProdottoIdProdotto(int idCarrello, int idProdotto);

    public OggettoCarrello getById(int idOggettoCarrello);

    public void cancella(OggettoCarrello o);
}
