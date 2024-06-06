package com.bytebazaar.bytebazaar.exception.messaggiException;

public class UnAuthorizedException extends RuntimeException
{
    /**
     * Costruisce un'eccezione UnAuthorizedException con un messaggio specificato.
     * @param message Il messaggio di errore associato all'eccezione.
     */
    public UnAuthorizedException(String message) {
        // Chiama il costruttore della classe padre (Exception) con il messaggio specificato
        super(message);
    }
}
