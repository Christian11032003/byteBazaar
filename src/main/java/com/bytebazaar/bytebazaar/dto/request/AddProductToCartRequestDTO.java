package com.bytebazaar.bytebazaar.dto.request;

import lombok.Data;

@Data
public class AddProductToCartRequestDTO {

    private int idProdotto;

    private int quantita;

}
