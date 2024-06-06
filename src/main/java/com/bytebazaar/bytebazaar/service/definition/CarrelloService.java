package com.bytebazaar.bytebazaar.service.definition;

import com.bytebazaar.bytebazaar.model.Carrello;

import java.util.Optional;

// Interfaccia per il servizio relativo alla gestione del carrello degli acquisti
public interface CarrelloService {

    /**
     * Metodo per ottenere il carrello di un utente tramite il nome utente.
     *
     * @param username il nome utente associato al carrello da recuperare
     * @return un Optional contenente il Carrello se trovato, altrimenti un Optional vuoto
     */
    public Optional<Carrello> getByUsername(String username);

    /**
     * Metodo per salvare un carrello nel database.
     *
     * @param c il carrello da salvare
     */
    public void salva(Carrello c);
}
