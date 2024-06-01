package com.bytebazaar.bytebazaar.dto.request;

import com.bytebazaar.bytebazaar.model.Condizione;
import lombok.Data;

@Data
public class InsertOrModifyProductRequestDTO
{

    private String nome;

    private String descrizione;

    private double prezzo;

    private int quantita;

    private Condizione condizione;


}
