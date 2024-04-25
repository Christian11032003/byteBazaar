package com.bytebazaar.bytebazaar.dto.request.feedback;

import lombok.Data;
@Data
public class AddFeedbackRequest
{
    private int idProdotto;

    private String descrizione;

    private int valutazione;

}
