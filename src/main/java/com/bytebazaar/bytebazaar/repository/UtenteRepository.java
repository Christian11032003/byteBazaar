package com.bytebazaar.bytebazaar.repository;

import com.bytebazaar.bytebazaar.model.Ruolo;
import com.bytebazaar.bytebazaar.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


// Definizione dell'interfaccia UtenteRepository che estende JpaRepository per l'entità Utente con chiave primaria di tipo Integer
public interface UtenteRepository extends JpaRepository<Utente, Integer> {

        /**
         * Metodo per trovare un utente per ID.
         * Restituisce un Optional contenente l'Utente se trovato, altrimenti un Optional vuoto.
         *
         * @param idutente l'ID dell'utente da cercare
         * @return un Optional contenente l'Utente se trovato, altrimenti un Optional vuoto
         */
        Optional<Utente> findById(int idutente);

        /**
         * Metodo per trovare tutti gli utenti con un determinato ruolo.
         * Restituisce una lista di utenti che hanno il ruolo specificato.
         *
         * @param ruolo il ruolo da cercare
         * @return una lista di utenti che hanno il ruolo specificato
         */
        List<Utente> findAllByRuolo(Ruolo ruolo);

        /**
         * Metodo per trovare un utente per nome utente.
         * Restituisce un Optional contenente l'Utente se trovato, altrimenti un Optional vuoto.
         *
         * @param username il nome utente dell'utente da cercare
         * @return un Optional contenente l'Utente se trovato, altrimenti un Optional vuoto
         */
        Optional<Utente> findByUsernameAndPassword(String username, String password);

        Optional<Utente> findByUsername(String username);

}
