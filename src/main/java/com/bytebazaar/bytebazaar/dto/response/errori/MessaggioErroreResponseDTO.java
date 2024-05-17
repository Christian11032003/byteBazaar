package com.bytebazaar.bytebazaar.dto.response.errori;

import lombok.Data;

@Data
public class MessaggioErroreResponseDTO
{
    private String errore;
    private String messaggio;

    public MessaggioErroreResponseDTO(String errore, String messaggio)
    {
        this.errore = errore;
        this.messaggio = messaggio;
    }
}
