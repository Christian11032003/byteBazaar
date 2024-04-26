package com.bytebazaar.bytebazaar.dto.response;


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

    public static class Builder
    {
        private String username;
        private String ruolo;

        public Builder setUsername(String username)
        {
            this.username = username;
            return this;
        }

        public Builder setRuolo(String ruolo)
        {
            this.ruolo = ruolo;
            return this;
        }

        public LoginResponseDTO build()
        {
            return new LoginResponseDTO(username,ruolo);
        }

    }
}
