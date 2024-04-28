package com.bytebazaar.bytebazaar.service.definition;


import com.bytebazaar.bytebazaar.model.Oggettocarrello;

public interface OggettoCarrelloService
{
    public void salva(Oggettocarrello o);

    public Oggettocarrello getByCarrelloIdcarrelloAndProdottoIdProdotto(int idCarrello, int idProdotto);

    public Oggettocarrello getById(int idOggettoCarrello);

    public void cancella(Oggettocarrello o);
}
