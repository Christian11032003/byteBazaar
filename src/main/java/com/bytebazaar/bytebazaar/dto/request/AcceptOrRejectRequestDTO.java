package com.bytebazaar.bytebazaar.dto.request;

import com.bytebazaar.bytebazaar.model.Stato;
import lombok.Data;

// DTO per la richiesta di accettare o rifiutare una richiesta
@Data
public class AcceptOrRejectRequestDTO
{

    // Identificativo univoco della richiesta
    private int idRichiesta;

    // Stato della richiesta (accettato o rifiutato)
    private Stato stato;
}
