package com.bytebazaar.bytebazaar.repository;

import com.bytebazaar.bytebazaar.model.Carrello;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Definizione dell'interfaccia CarrelloRepository che estende JpaRepository per l'entità Carrello con chiave primaria di tipo Integer
public interface CarrelloRepository extends JpaRepository<Carrello, Integer> {

    /**
     * Metodo per trovare un carrello associato a un utente tramite il nome utente e dove la data di acquisto è nulla.
     * Restituisce un Optional contenente il Carrello se trovato, altrimenti un Optional vuoto.
     *
     * @param username il nome utente associato al carrello da cercare
     * @return un Optional contenente il Carrello se trovato, altrimenti un Optional vuoto
     */
    public Optional<Carrello> findCarrelloByUtente_UsernameAndDataacquistoIsNull(String username);
}
