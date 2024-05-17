package com.bytebazaar.bytebazaar.dto.request;

import lombok.Data;

@Data
public class ModifyFeedbackRequestDTO
{
    private int idFeedback;

    private String descrizione;

    private int valutazione;
}
