package com.bytebazaar.bytebazaar.dto.request;

import lombok.Data;

// DTO per la richiesta di trovare info su utente specifico
@Data
public class FindThingsRequestDTO
{
    // Identificativo univoco dell'utente
    private int idUtente;
}
