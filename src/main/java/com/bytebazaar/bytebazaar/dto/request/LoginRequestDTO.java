package com.bytebazaar.bytebazaar.dto.request;

import lombok.Data;

// DTO per la richiesta
@Data
public class LoginRequestDTO
{

    // Nome utente dell'utente che sta effettuando il login
    private String username;

    // Password dell'utente che sta effettuando il login
    private String password;
}
