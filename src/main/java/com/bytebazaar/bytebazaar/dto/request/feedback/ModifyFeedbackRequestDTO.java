package com.bytebazaar.bytebazaar.dto.request.feedback;

import lombok.Data;

@Data
public class ModifyFeedbackRequestDTO
{
    int idFeedback;

    private String descrizione;

    private int valutazione;
}
