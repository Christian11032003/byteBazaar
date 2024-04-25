package com.bytebazaar.bytebazaar.dto.request.utente;

import lombok.Data;

@Data
public class LoginRequest
{
    private String username;
    private String password;
}
