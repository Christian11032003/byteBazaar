package com.bytebazaar.bytebazaar.dto.request;

import lombok.Data;

@Data
public class AddProductToCartRequest {

    private int idProdotto;

    private int quantita;

}
