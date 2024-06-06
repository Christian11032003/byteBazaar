package com.bytebazaar.bytebazaar.dto.request;

import com.bytebazaar.bytebazaar.model.Ruolo;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

// DTO per la richiesta
@Data
public class RegistrationUserRequestDTO
{
    // Nome dell'utente
    @NotBlank(message = "il nome non può essere null")
    private String nome;

    // Cognome dell'utente
    @NotBlank(message = "il cognome non può essere null")
    private String cognome;

    // Email dell'utente
    @NotBlank(message = "l'email non può essere unica")
    private String email;

    // Nome utente dell'utente
    private String username;

    // Password dell'utente
    @NotBlank(message = "la password non può essere null o vuota")
    private String password;

    // Ripetizione della password dell'utente
    @NotBlank(message = "la password ripetuta non può essere null o vuota")
    private String passwordRipetuta;

    // Ruolo dell'utente
    @Enumerated(EnumType.STRING)
    private Ruolo ruolo;

}
