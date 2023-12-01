package com.bytebazaar.bytebazaar.dto.request;

import lombok.Data;

@Data
public class RegistrationProdottoRequest
{
    private String immagine;

    private String nome;

    private String descrizione;

    private double prezzo;

    private int quantita;

    private String username;

    private String password;
}
