package com.bytebazaar.bytebazaar.dto.request;

import com.bytebazaar.bytebazaar.model.Ruolo;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

@Data
public class RegistrationUtenteRequest
{

    private String nome;

    private String cognome;

    @NotBlank(message = "l'email non può essere unica")
    @UniqueElements(message = "l'email deve essere unica")
    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,7}$",message = "formato email non rispettato")
    private String email;

    private String username;

    @NotBlank(message = "la password non può essere null o vuota")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = "inserisci un carattere maiuscolo, una minuscola, un carattere speciale e un numero, tra gli 8 e i 20 caratteri")
    private String password;

    @NotBlank(message = "la password ripetuta non può essere null o vuota")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = "inserisci un carattere maiuscolo, una minuscola, un carattere speciale e un numero, tra gli 8 e i 20 caratteri")
    private String passwordRipetuta;

    @NotBlank(message = "il ruolo non può essere null")
    @Enumerated(EnumType.STRING)
    private Ruolo ruolo;





}
