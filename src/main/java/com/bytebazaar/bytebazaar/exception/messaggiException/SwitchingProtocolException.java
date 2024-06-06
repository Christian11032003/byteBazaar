package com.bytebazaar.bytebazaar.exception.messaggiException;

public class SwitchingProtocolException extends RuntimeException
{
    /**
     * Costruisce un'eccezione SwitchingProtocolException con un messaggio specificato.
     * @param message Il messaggio di errore associato all'eccezione.
     */
    public SwitchingProtocolException(String message) {
        // Chiama il costruttore della classe padre (Exception) con il messaggio specificato
        super(message);
    }
}
