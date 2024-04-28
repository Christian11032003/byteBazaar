package com.bytebazaar.bytebazaar.service.definition;


import com.bytebazaar.bytebazaar.model.Oggettocarrello;

import java.util.Optional;

public interface OggettoCarrelloService
{
    public void salva(Oggettocarrello o);

    public Optional<Oggettocarrello> getByCarrelloIdcarrelloAndProdottoIdProdotto(int idCarrello, int idProdotto);

    public Optional<Oggettocarrello> getById(int idOggettoCarrello);

    public void cancella(Oggettocarrello o);
}
