package com.bytebazaar.bytebazaar.exception.messaggiException;

public class NotFoundException extends RuntimeException
{
    /**
     * Costruisce un'eccezione NotFoundException con un messaggio specificato.
     * @param message Il messaggio di errore associato all'eccezione.
     */
    public NotFoundException(String message) {
        // Chiama il costruttore della classe padre (Exception) con il messaggio specificato
        super(message);
    }
}
