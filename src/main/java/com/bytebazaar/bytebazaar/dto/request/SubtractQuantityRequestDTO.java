package com.bytebazaar.bytebazaar.dto.request;

import lombok.Data;
// DTO per la richiesta
@Data
public class SubtractQuantityRequestDTO
{
    // Identificativo univoco dell'oggetto nel carrello
    private int idOggettocarrello;

    // Quantità dell'oggetto nel carrello
    private int quantita;

}
