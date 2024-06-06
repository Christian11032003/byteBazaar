package com.bytebazaar.bytebazaar.repository;

import com.bytebazaar.bytebazaar.model.Richiesta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


// Definizione dell'interfaccia RichiestaRepository che estende JpaRepository per l'entità Richiesta con chiave primaria di tipo Integer
public interface RichiestaRepository extends JpaRepository<Richiesta, Integer> {

    /**
     * Metodo per trovare una richiesta per ID.
     * Restituisce un Optional contenente la Richiesta se trovata, altrimenti un Optional vuoto.
     *
     * @param idrichiesta l'ID della richiesta da cercare
     * @return un Optional contenente la Richiesta se trovata, altrimenti un Optional vuoto
     */
    Optional<Richiesta> findById(int idrichiesta);

    /**
     * Metodo per trovare una richiesta per il nome utente dell'utente associato.
     * Restituisce un Optional contenente la Richiesta se trovata, altrimenti un Optional vuoto.
     *
     * @param username il nome utente dell'utente associato alla richiesta
     * @return un Optional contenente la Richiesta se trovata, altrimenti un Optional vuoto
     */
    Optional<Richiesta> findByUtente_Username(String username);
}
