package com.bytebazaar.bytebazaar.service.definition;

import com.bytebazaar.bytebazaar.model.Messaggio;

// Interfaccia per il servizio relativo alla gestione dei messaggi
public interface MessaggioService {
    /**
     * Metodo per salvare un messaggio nel database.
     *
     * @param m il messaggio da salvare
     */
    public void salva(Messaggio m);
}
