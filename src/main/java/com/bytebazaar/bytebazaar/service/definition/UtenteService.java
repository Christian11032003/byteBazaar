package com.bytebazaar.bytebazaar.service.definition;

import com.bytebazaar.bytebazaar.model.Ruolo;
import com.bytebazaar.bytebazaar.model.Utente;

import java.util.List;


// Interfaccia per il servizio relativo alla gestione degli utenti
public interface UtenteService {
    /**
     * Metodo per ottenere un utente tramite il suo nome utente.
     *
     * @param username il nome utente dell'utente da cercare
     * @return l'utente trovato, null se non trovato
     */
    public Utente getByUsername(String username);

    /**
     * Metodo per ottenere un utente tramite il suo ID.
     *
     * @param id l'ID dell'utente da cercare
     * @return l'utente trovato, null se non trovato
     */
    public Utente getById(int id);

    /**
     * Metodo per trovare tutti gli utenti con un determinato ruolo.
     *
     * @param ruolo il ruolo degli utenti da trovare
     * @return una lista di utenti che hanno il ruolo specificato
     */
    public List<Utente> findAllByRuolo(Ruolo ruolo);

    /**
     * Metodo per salvare un utente nel database.
     *
     * @param u l'utente da salvare
     */
    public void salva(Utente u);
}
