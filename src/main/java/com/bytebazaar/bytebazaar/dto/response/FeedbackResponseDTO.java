package com.bytebazaar.bytebazaar.dto.response;

import com.bytebazaar.bytebazaar.exception.messaggiException.BadRequestException;
import lombok.Getter;

//DTO per la response
@Getter
public class FeedbackResponseDTO
{
    // Identificativo univoco del prodotto associato al feedback
    private int idProdotto;

    // Descrizione del feedback
    private String descrizione;

    // Valutazione assegnata al feedback
    private int valutazione;

    // Costruttore della classe che accetta un idProdotto, una descrizione e una valutazione come parametri
    public FeedbackResponseDTO(int idProdotto, String descrizione, int valutazione) {
        this.idProdotto = idProdotto;
        this.descrizione = descrizione;
        this.valutazione = valutazione;
    }

    // Classe statica interna utilizzata per la costruzione di un oggetto FeedbackResponseDTO in modo fluente
    public static class BuilderFeedbackResponseDTO {

        // Attributi temporanei utilizzati durante la costruzione dell'oggetto
        private int idProdotto;
        private String descrizione;
        private int valutazione;

        /**
         * Imposta l'identificativo univoco del prodotto associato al feedback
         * @param idProdotto Identificativo univoco del prodotto
         * @return BuilderFeedbackResponseDTO
         */
        public BuilderFeedbackResponseDTO setIdProdotto(int idProdotto) {
            this.idProdotto = idProdotto;
            return this;
        }

        /**
         * Imposta la descrizione del feedback
         * @param descrizione Descrizione del feedback
         * @return BuilderFeedbackResponseDTO
         */
        public BuilderFeedbackResponseDTO setDescrizione(String descrizione) {
            this.descrizione = descrizione;
            return this;
        }

        /**
         * Imposta la valutazione assegnata al feedback
         * @param valutazione Valutazione assegnata al feedback
         * @return BuilderFeedbackResponseDTO
         */
        public BuilderFeedbackResponseDTO setValutazione(int valutazione) {
            this.valutazione = valutazione;
            return this;
        }

        /**
         * Verifica se i dati forniti sono validi per la costruzione di un oggetto FeedbackResponseDTO
         * @return true se i dati sono validi, altrimenti false
         */
        public boolean isValid() {
            return idProdotto > 0 && descrizione != null && valutazione > 0;
        }

        /**
         * Costruisce un oggetto FeedbackResponseDTO con i dati forniti
         * @return Oggetto FeedbackResponseDTO
         * @throws BadRequestException se i dati forniti non sono validi
         */
        public FeedbackResponseDTO build() {
            if (isValid()) {
                return new FeedbackResponseDTO(idProdotto, descrizione, valutazione);
            }

            throw new BadRequestException("Impossibile aggiungere il feedback");
        }
    }
}
