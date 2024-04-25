package com.bytebazaar.bytebazaar.dto.request.oggettoCarello;

import lombok.Data;

@Data
public class AddProductToCartRequest {

    private int idProdotto;

    private int quantita;

}
