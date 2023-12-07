package com.bytebazaar.bytebazaar.dto.request;

import lombok.Data;

@Data
public class MandaMessaggioRequest
{
    private int idprodotto;

    private String testoMessaggio;

    private String username;

    private String password;


}
