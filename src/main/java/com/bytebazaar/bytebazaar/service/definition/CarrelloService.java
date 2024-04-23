package com.bytebazaar.bytebazaar.service.definition;

import com.bytebazaar.bytebazaar.model.Carrello;
import com.bytebazaar.bytebazaar.model.Utente;

public interface CarrelloService
{
    public Carrello getByUsername(String username);
    public void confermaCarrello(Carrello c);


}
