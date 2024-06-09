package com.bytebazaar.bytebazaar.dto.request;

import lombok.Data;

// DTO per la richiesta per bannare o sbannare un utente
@Data
public class BannedOrUnBannedRequestDTO {

    // Identificativo univoco dell'utente
    private int idUtente;

}
