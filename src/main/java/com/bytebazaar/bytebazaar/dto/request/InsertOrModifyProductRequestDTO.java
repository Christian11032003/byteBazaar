package com.bytebazaar.bytebazaar.dto.request;

import com.bytebazaar.bytebazaar.model.Condizione;
import lombok.Data;

// DTO per la richiesta di inserire o modificare il prodotto in vendita
@Data
public class InsertOrModifyProductRequestDTO
{

    // Nome del prodotto
    private String nome;

    // Descrizione del prodotto
    private String descrizione;

    // Prezzo del prodotto
    private double prezzo;

    // Quantità disponibile del prodotto
    private int quantita;

    // Condizione del prodotto
    private Condizione condizione;

}
