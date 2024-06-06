package com.bytebazaar.bytebazaar.service.definition;

import com.bytebazaar.bytebazaar.model.Prodotto;

// Interfaccia per il servizio relativo alla gestione dei prodotti
public interface ProdottoService {
    /**
     * Metodo per ottenere un prodotto tramite il suo nome.
     *
     * @param name il nome del prodotto da cercare
     * @return il prodotto trovato, null se non trovato
     */
    public Prodotto getByNome(String name);

    /**
     * Metodo per salvare un prodotto nel database.
     *
     * @param p il prodotto da salvare
     */
    public void salva(Prodotto p);
}
