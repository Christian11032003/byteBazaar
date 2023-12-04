package com.bytebazaar.bytebazaar.dto.request;

import com.bytebazaar.bytebazaar.model.Carrello;
import com.bytebazaar.bytebazaar.model.Prodotto;
import lombok.Data;

@Data
public class AggiungiProdottoInCarrelloRequest {

    private String nomeProdotto;

    private int quantita;

    private String username;

    private String password;
}
