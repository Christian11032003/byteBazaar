package com.bytebazaar.bytebazaar.dto.request.utente;

import com.bytebazaar.bytebazaar.model.Ruolo;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RegistrationUserRequestDTO
{
    @NotBlank(message = "il nome non può essere null")
    private String nome;

    @NotBlank(message = "il cognome non può essere null")
    private String cognome;

    @NotBlank(message = "l'email non può essere unica")
    private String email;

    private String username;

    @NotBlank(message = "la password non può essere null o vuota")
    private String password;

    @NotBlank(message = "la password ripetuta non può essere null o vuota")
    private String passwordRipetuta;

    @Enumerated(EnumType.STRING)
    private Ruolo ruolo;

}
