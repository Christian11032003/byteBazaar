package com.bytebazaar.bytebazaar.service.definition;


import com.bytebazaar.bytebazaar.model.Oggettocarrello;

import java.util.Optional;

// Interfaccia per il servizio relativo alla gestione degli oggetti nel carrello degli acquisti
public interface OggettoCarrelloService {
    /**
     * Metodo per salvare un oggetto carrello nel database.
     *
     * @param o l'oggetto carrello da salvare
     */
    public void salva(Oggettocarrello o);

    /**
     * Metodo per ottenere un oggetto carrello specifico tramite l'ID del carrello e l'ID del prodotto.
     *
     * @param idCarrello l'ID del carrello
     * @param idProdotto l'ID del prodotto
     * @return un Optional contenente l'oggetto carrello se trovato, altrimenti un Optional vuoto
     */
    public Optional<Oggettocarrello> getByCarrelloIdcarrelloAndProdottoIdProdotto(int idCarrello, int idProdotto);

    /**
     * Metodo per ottenere un oggetto carrello specifico tramite il suo ID.
     *
     * @param idOggettoCarrello l'ID dell'oggetto carrello da ottenere
     * @return un Optional contenente l'oggetto carrello se trovato, altrimenti un Optional vuoto
     */
    public Optional<Oggettocarrello> getById(int idOggettoCarrello);

    /**
     * Metodo per cancellare un oggetto carrello dal database.
     *
     * @param o l'oggetto carrello da cancellare
     */
    public void cancella(Oggettocarrello o);
}
