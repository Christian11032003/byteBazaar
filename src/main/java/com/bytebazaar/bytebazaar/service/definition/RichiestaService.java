package com.bytebazaar.bytebazaar.service.definition;

import com.bytebazaar.bytebazaar.model.Richiesta;

import java.util.Optional;


// Interfaccia per il servizio relativo alla gestione delle richieste
public interface RichiestaService {
    /**
     * Metodo per trovare una richiesta per il nome utente dell'utente associato.
     *
     * @param username il nome utente associato alla richiesta da trovare
     * @return un Optional contenente la richiesta se trovata, altrimenti un Optional vuoto
     */
    public Optional<Richiesta> findByUtenteUsername(String username);

    /**
     * Metodo per salvare una richiesta nel database.
     *
     * @param r la richiesta da salvare
     */
    public void salva(Richiesta r);

    /**
     * Metodo per trovare una richiesta specifica tramite il suo ID.
     *
     * @param idRichiesta l'ID della richiesta da trovare
     * @return la Richiesta associata all'ID specificato
     */
    public Richiesta findByIdrichiesta(int idRichiesta);
}
