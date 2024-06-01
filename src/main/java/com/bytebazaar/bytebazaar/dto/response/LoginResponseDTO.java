package com.bytebazaar.bytebazaar.dto.response;


import com.bytebazaar.bytebazaar.exception.messaggiException.BadRequestException;
import lombok.Getter;

@Getter
public class LoginResponseDTO
{

    private final String username;
    private final String ruolo;

    private LoginResponseDTO(String username, String ruolo) {
        this.username = username;
        this.ruolo = ruolo;
    }

    public static class BuilderLoginResponseDTO
    {
        private String username;
        private String ruolo;

        public BuilderLoginResponseDTO setUsername(String username)
        {
            this.username = username;
            return this;
        }

        public BuilderLoginResponseDTO setRuolo(String ruolo)
        {
            this.ruolo = ruolo;
            return this;
        }

        public boolean isValid()
        {
            return username!=null && ruolo != null;
        }

        public LoginResponseDTO build()
        {
            if(isValid())
            {
                return new LoginResponseDTO(username,ruolo);
            }

            throw new BadRequestException("utente non trovato");

        }

    }
}
