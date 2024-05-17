package com.bytebazaar.bytebazaar.dto.request;

import lombok.Data;
@Data
public class AddFeedbackRequestDTO
{
    private int idProdotto;

    private String descrizione;

    private int valutazione;

}
