package com.bytebazaar.bytebazaar.dto.response;

import com.bytebazaar.bytebazaar.exception.messaggiException.BadRequestException;
import com.bytebazaar.bytebazaar.model.Ruolo;
import lombok.Getter;
@Getter
public class RegistrationUserResponseDTO
{
    private String nome;
    private String cognome;
    private String email;
    private String username;
    private String password;
    private Ruolo ruolo;

    public RegistrationUserResponseDTO(String nome, String cognome, String email, String username, String password, Ruolo ruolo) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.username = username;
        this.password = password;
        this.ruolo = ruolo;
    }

    public static class BuilderRegistrationUserDTO
    {
        private String nome;
        private String cognome;
        private String email;
        private String username;
        private String password;
        private Ruolo ruolo;

        public BuilderRegistrationUserDTO setNome(String nome) {
            this.nome = nome;
            return this;
        }

        public BuilderRegistrationUserDTO setCognome(String cognome) {
            this.cognome = cognome;
            return this;
        }

        public BuilderRegistrationUserDTO setEmail(String email) {
            this.email = email;
            return this;
        }

        public BuilderRegistrationUserDTO setUsername(String username) {
            this.username = username;
            return this;
        }

        public BuilderRegistrationUserDTO setPassword(String password) {
            this.password = password;
            return this;
        }

        public BuilderRegistrationUserDTO setRuolo(Ruolo ruolo) {
            this.ruolo = ruolo;
            return this;
        }

        private boolean isValid(){
            return nome != null && cognome != null && email != null && username != null && password != null && ruolo != null;
        }


        public RegistrationUserResponseDTO build()
        {
            if(isValid())
            {
                return new RegistrationUserResponseDTO(nome,cognome,email,username,password,ruolo);
            }

            throw new BadRequestException("Impossibile registrarsi, controllare che tutti i campi siano pieni");
        }

    }



}
