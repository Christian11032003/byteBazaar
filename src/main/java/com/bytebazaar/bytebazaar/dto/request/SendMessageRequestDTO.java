package com.bytebazaar.bytebazaar.dto.request;

import lombok.Data;
// DTO per la richiesta
@Data
public class SendMessageRequestDTO
{
    // Identificativo univoco dell'utente a cui inviare il messaggio
    private int idUtente;

    // Testo del messaggio da inviare
    private String testoMessaggio;




}
