package com.bytebazaar.bytebazaar.dto.response;


import com.bytebazaar.bytebazaar.exception.messaggiException.BadRequestException;
import lombok.Getter;

//DTO per la response
@Getter
public class LoginResponseDTO
{

    // Username dell'utente
    private final String username;

    // Ruolo dell'utente
    private final String ruolo;

    // Costruttore privato che accetta username e ruolo come parametri
    private LoginResponseDTO(String username, String ruolo) {
        this.username = username;
        this.ruolo = ruolo;
    }

    // Classe statica interna utilizzata per la costruzione di un oggetto LoginResponseDTO in modo fluente
    public static class BuilderLoginResponseDTO {

        // Attributi temporanei utilizzati durante la costruzione dell'oggetto
        private String username;
        private String ruolo;

        /**
         * Imposta lo username dell'utente
         * @param username Username dell'utente
         * @return BuilderLoginResponseDTO
         */
        public BuilderLoginResponseDTO setUsername(String username) {
            this.username = username;
            return this;
        }

        /**
         * Imposta il ruolo dell'utente
         * @param ruolo Ruolo dell'utente
         * @return BuilderLoginResponseDTO
         */
        public BuilderLoginResponseDTO setRuolo(String ruolo) {
            this.ruolo = ruolo;
            return this;
        }

        /**
         * Verifica se i dati forniti sono validi per la costruzione di un oggetto LoginResponseDTO
         * @return true se i dati sono validi, altrimenti false
         */
        public boolean isValid() {
            return username != null && ruolo != null;
        }

        /**
         * Costruisce un oggetto LoginResponseDTO con i dati forniti
         * @return Oggetto LoginResponseDTO
         * @throws BadRequestException se i dati forniti non sono validi
         */
        public LoginResponseDTO build() {
            if (isValid()) {
                return new LoginResponseDTO(username, ruolo);
            }
            throw new BadRequestException("Utente non trovato");
        }
    }
}
