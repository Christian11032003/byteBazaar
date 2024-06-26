package com.bytebazaar.bytebazaar.dto.request;

import lombok.Data;

// DTO per la richiesta di cancellare un prodotto dal carrello
@Data
public class DeleteObjectFromCartRequestDTO
{
    // Identificativo univoco dell'oggetto nel carrello
    private int idOggettoCarrello;

}
