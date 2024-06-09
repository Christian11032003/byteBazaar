package com.bytebazaar.bytebazaar.dto.request;

import com.bytebazaar.bytebazaar.model.Condizione;
import lombok.Data;

// DTO per la richiesta di filtraggio prodotto della condizione
@Data
public class FilterProductRequestDTO
{
    // Condizione del prodotto
    private Condizione condizione;
}
