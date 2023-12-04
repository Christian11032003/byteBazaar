package com.bytebazaar.bytebazaar.dto.request;

import com.bytebazaar.bytebazaar.model.Utente;
import lombok.Data;

@Data
public class CreaCarrelloRequest
{
    private Utente utente;
}
