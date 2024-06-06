package com.bytebazaar.bytebazaar.exception.messaggiException;

public class AlreadyReportedException extends RuntimeException
{

    /**
     * Costruisce un'eccezione AlreadyReportedException con un messaggio specificato.
     * @param message Il messaggio di errore associato all'eccezione.
     */
    public AlreadyReportedException(String message) {
        // Chiama il costruttore della classe padre (Exception) con il messaggio specificato
        super(message);
    }
}
