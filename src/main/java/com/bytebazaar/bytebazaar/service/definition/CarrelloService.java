package com.bytebazaar.bytebazaar.service.definition;

import com.bytebazaar.bytebazaar.model.Carrello;
import com.bytebazaar.bytebazaar.model.Utente;

import java.util.Optional;

public interface CarrelloService
{
    public Optional<Carrello> getByUsername(String username);
    public void salva(Carrello c);


}
