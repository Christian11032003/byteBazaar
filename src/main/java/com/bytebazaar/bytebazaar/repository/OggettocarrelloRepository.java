package com.bytebazaar.bytebazaar.repository;

import com.bytebazaar.bytebazaar.model.Oggettocarrello;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Definizione dell'interfaccia OggettocarrelloRepository che estende JpaRepository per l'entità Oggettocarrello con chiave primaria di tipo Integer
public interface OggettocarrelloRepository extends JpaRepository<Oggettocarrello, Integer> {

    /**
     * Metodo per trovare un oggetto carrello specifico tramite l'ID del carrello e l'ID del prodotto.
     * Restituisce un Optional contenente l'Oggettocarrello se trovato, altrimenti un Optional vuoto.
     *
     * @param idcarrello l'ID del carrello dell'oggetto carrello da cercare
     * @param idprodotto l'ID del prodotto dell'oggetto carrello da cercare
     * @return un Optional contenente l'Oggettocarrello se trovato, altrimenti un Optional vuoto
     */
    Optional<Oggettocarrello> findByCarrello_IdAndProdotto_Id(int idcarrello, int idprodotto);

    /**
     * Metodo per trovare un oggetto carrello specifico tramite il suo ID.
     * Restituisce un Optional contenente l'Oggettocarrello se trovato, altrimenti un Optional vuoto.
     *
     * @param idoggettocarrello l'ID dell'oggetto carrello da cercare
     * @return un Optional contenente l'Oggettocarrello se trovato, altrimenti un Optional vuoto
     */
    Optional<Oggettocarrello> findById(int idoggettocarrello);
}
