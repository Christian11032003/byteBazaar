package com.bytebazaar.bytebazaar.dto.request;

import lombok.Data;

// DTO per la richiesta
@Data
public class AddProductToCartRequestDTO {

    // Identificativo univoco del prodotto che si desidera aggiungere al carrello
    private int idProdotto;

    // Quantità del prodotto che si desidera aggiungere al carrello
    private int quantita;

}
