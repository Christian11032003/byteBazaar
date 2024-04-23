package com.bytebazaar.bytebazaar.service.definition;

import com.bytebazaar.bytebazaar.dto.request.BannedOrUnBannedRequest;
import com.bytebazaar.bytebazaar.dto.request.LoginRequest;
import com.bytebazaar.bytebazaar.dto.request.RegistrationUserRequest;
import com.bytebazaar.bytebazaar.model.Prodotto;
import com.bytebazaar.bytebazaar.model.Ruolo;
import com.bytebazaar.bytebazaar.model.Utente;

import java.util.List;


public interface UtenteService
{


    //funzionalità per applicare il facade
    public Utente getByUsername(String username);
    public Utente getById(int id);
    public List<Utente> findAllByRuolo(Ruolo ruolo);
    public void salva(Utente u);


}
