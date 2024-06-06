package com.bytebazaar.bytebazaar.exception.messaggiException;

public class BadRequestException extends RuntimeException
{
    /**
     * Costruisce un'eccezione BadRequestException con un messaggio specificato.
     * @param message Il messaggio di errore associato all'eccezione.
     */
    public BadRequestException(String message) {
        // Chiama il costruttore della classe padre (Exception) con il messaggio specificato
        super(message);
    }
}
