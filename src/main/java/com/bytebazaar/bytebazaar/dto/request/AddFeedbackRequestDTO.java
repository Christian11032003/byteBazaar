package com.bytebazaar.bytebazaar.dto.request;

import lombok.Data;

// DTO per la richiesta
@Data
public class AddFeedbackRequestDTO
{
    // Identificativo univoco del prodotto a cui si riferisce il feedback
    private int idProdotto;

    // Descrizione del feedback fornita dall'utente
    private String descrizione;

    // Valutazione assegnata al prodotto, generalmente un numero intero che rappresenta il punteggio
    private int valutazione;

}
