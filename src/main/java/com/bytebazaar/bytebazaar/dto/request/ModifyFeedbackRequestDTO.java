package com.bytebazaar.bytebazaar.dto.request;

import lombok.Data;

// DTO per la richiesta
@Data
public class ModifyFeedbackRequestDTO
{
    // Identificativo univoco del feedback
    private int idFeedback;

    // Descrizione del feedback fornita dall'utente
    private String descrizione;

    // Valutazione assegnata al feedback
    private int valutazione;
}
