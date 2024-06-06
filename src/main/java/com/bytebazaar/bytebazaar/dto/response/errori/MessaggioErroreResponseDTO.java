package com.bytebazaar.bytebazaar.dto.response.errori;

import lombok.Data;

//DTO per la response
@Data
public class MessaggioErroreResponseDTO
{
    // Stringa che rappresenta il tipo di errore
    private String errore;

    // Messaggio esplicativo associato all'errore
    private String messaggio;

    // Costruttore della classe che accetta un errore e un messaggio come parametri
    public MessaggioErroreResponseDTO(String errore, String messaggio) {
        this.errore = errore;
        this.messaggio = messaggio;
    }
}
