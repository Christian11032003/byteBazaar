package com.bytebazaar.bytebazaar.dto.request;

import lombok.Data;

// DTO per la richiesta
@Data
public class DeleteObjectFromCartRequestDTO
{
    // Identificativo univoco dell'oggetto nel carrello
    private int idOggettocarrello;

}
