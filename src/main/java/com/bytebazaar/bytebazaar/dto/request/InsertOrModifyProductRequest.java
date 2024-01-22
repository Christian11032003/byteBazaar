package com.bytebazaar.bytebazaar.dto.request;

import lombok.Data;

@Data
public class InsertOrModifyProductRequest
{
    private String immagine;

    private String nome;

    private String descrizione;

    private double prezzo;

    private int quantita;


}
