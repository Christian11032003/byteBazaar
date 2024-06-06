package com.bytebazaar.bytebazaar.dto.response;

import com.bytebazaar.bytebazaar.exception.messaggiException.BadRequestException;
import com.bytebazaar.bytebazaar.model.Ruolo;
import lombok.Getter;
//DTO per la response
@Getter
public class RegistrationUserResponseDTO
{
    // Campo privato che rappresenta il nome dell'utente registrato
    private String nome;

    // Campo privato che rappresenta il cognome dell'utente registrato
    private String cognome;

    // Campo privato che rappresenta l'email dell'utente registrato
    private String email;

    // Campo privato che rappresenta lo username dell'utente registrato
    private String username;

    // Campo privato che rappresenta la password dell'utente registrato
    private String password;

    // Campo privato che rappresenta il ruolo dell'utente registrato
    private Ruolo ruolo;

    // Costruttore che prende tutte le informazioni dell'utente per la registrazione
    public RegistrationUserResponseDTO(String nome, String cognome, String email, String username, String password, Ruolo ruolo) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.username = username;
        this.password = password;
        this.ruolo = ruolo;
    }

    // Classe Builder per creare oggetti RegistrationUserResponseDTO in modo flessibile
    public static class BuilderRegistrationUserDTO {

        // Campi privati del builder corrispondenti alle informazioni dell'utente
        private String nome;
        private String cognome;
        private String email;
        private String username;
        private String password;
        private Ruolo ruolo;

        // Metodo setter per impostare il nome e ritornare l'istanza del builder
        public BuilderRegistrationUserDTO setNome(String nome) {
            this.nome = nome;
            return this;
        }

        // Metodo setter per impostare il cognome e ritornare l'istanza del builder
        public BuilderRegistrationUserDTO setCognome(String cognome) {
            this.cognome = cognome;
            return this;
        }

        // Metodo setter per impostare l'email e ritornare l'istanza del builder
        public BuilderRegistrationUserDTO setEmail(String email) {
            this.email = email;
            return this;
        }

        // Metodo setter per impostare lo username e ritornare l'istanza del builder
        public BuilderRegistrationUserDTO setUsername(String username) {
            this.username = username;
            return this;
        }

        // Metodo setter per impostare la password e ritornare l'istanza del builder
        public BuilderRegistrationUserDTO setPassword(String password) {
            this.password = password;
            return this;
        }

        // Metodo setter per impostare il ruolo e ritornare l'istanza del builder
        public BuilderRegistrationUserDTO setRuolo(Ruolo ruolo) {
            this.ruolo = ruolo;
            return this;
        }

        // Metodo per verificare se tutti i campi necessari per la registrazione sono stati impostati
        public boolean isValid() {
            return nome != null && cognome != null && email != null && username != null && password != null && ruolo != null;
        }

        // Metodo per costruire l'oggetto RegistrationUserResponseDTO
        public RegistrationUserResponseDTO build() {
            // Verifica se tutti i campi sono impostati correttamente e lancia un'eccezione se non lo sono
            if (isValid()) {
                return new RegistrationUserResponseDTO(nome, cognome, email, username, password, ruolo);
            }
            // Altrimenti, lancia un'eccezione BadRequestException
            throw new BadRequestException("Impossibile registrarsi, controllare che tutti i campi siano pieni");
        }
    }



}
