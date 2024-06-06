package com.bytebazaar.bytebazaar.dto.response;

import com.bytebazaar.bytebazaar.exception.messaggiException.BadRequestException;
import lombok.Getter;

//DTO per la response
@Getter
public class OggettoCarrelloReponseDTO
{

    // ID dell'oggetto nel carrello
    private final int id;

    // Quantità dell'oggetto nel carrello
    private final int quantita;

    // Costruttore che accetta l'ID dell'oggetto e la quantità nel carrello
    public OggettoCarrelloReponseDTO(int id, int quantita) {
        this.id = id;
        this.quantita = quantita;
    }

    // Classe statica interna utilizzata per la costruzione di un oggetto OggettoCarrelloReponseDTO in modo fluente
    public static class BuilderOggettoCarrelloDTO {

        // Attributi temporanei utilizzati durante la costruzione dell'oggetto
        private int id;
        private int quantita;

        /**
         * Imposta l'ID dell'oggetto nel carrello
         * @param id ID dell'oggetto nel carrello
         * @return BuilderOggettoCarrelloDTO
         */
        public BuilderOggettoCarrelloDTO setId(int id) {
            this.id = id;
            return this;
        }

        /**
         * Imposta la quantità dell'oggetto nel carrello
         * @param quantita Quantità dell'oggetto nel carrello
         * @return BuilderOggettoCarrelloDTO
         */
        public BuilderOggettoCarrelloDTO setQuantita(int quantita) {
            this.quantita = quantita;
            return this;
        }

        /**
         * Verifica se i dati forniti sono validi per la costruzione di un oggetto OggettoCarrelloReponseDTO
         * @return true se i dati sono validi, altrimenti false
         */
        public boolean isValid(){
            return quantita >= 0 && id > 0;
        }

        /**
         * Costruisce un oggetto OggettoCarrelloReponseDTO con i dati forniti
         * @return OggettoCarrelloReponseDTO
         * @throws BadRequestException se i dati forniti non sono validi
         */
        public OggettoCarrelloReponseDTO build() {
            if(isValid()) {
                return new OggettoCarrelloReponseDTO(id, quantita);
            }
            throw new BadRequestException("Impossibile aggiungere al carrello");
        }
    }
}
