package com.bytebazaar.bytebazaar.dto.response.errori;

import lombok.Data;

@Data
public class MessaggioErroreResponse
{
    private String errore;
    private String messaggio;

    public MessaggioErroreResponse(String errore, String messaggio)
    {
        this.errore = errore;
        this.messaggio = messaggio;
    }
}
