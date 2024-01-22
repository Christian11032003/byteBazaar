package com.bytebazaar.bytebazaar.dto.request;

import lombok.Data;
@Data
public class AddFeedbackRequest
{
    private int idprodotto;

    private String descrizione;

    private int valutazione;

}
