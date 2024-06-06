package com.bytebazaar.bytebazaar.dto.response;


import com.bytebazaar.bytebazaar.exception.messaggiException.BadRequestException;
import lombok.Getter;

//DTO per la response
@Getter
public class MessaggioResponseDTO
{
    // ID del destinatario del messaggio
    private final int idDestinatario;

    // Testo del messaggio
    private final String testo;

    // Costruttore che accetta l'ID del destinatario e il testo del messaggio
    public MessaggioResponseDTO(int idDestinatario, String testo) {
        this.idDestinatario = idDestinatario;
        this.testo = testo;
    }

    // Classe statica interna utilizzata per la costruzione di un oggetto MessaggioResponseDTO in modo fluente
    public static class BuilderMessaggioResponseDTO {

        // Attributi temporanei utilizzati durante la costruzione dell'oggetto
        private int idDestinatario;
        private String testo;

        /**
         * Imposta l'ID del destinatario del messaggio
         * @param idDestinatario ID del destinatario del messaggio
         * @return BuilderMessaggioResponseDTO
         */
        public BuilderMessaggioResponseDTO setIdDestinatario(int idDestinatario) {
            this.idDestinatario = idDestinatario;
            return this;
        }

        /**
         * Imposta il testo del messaggio
         * @param testo Testo del messaggio
         * @return BuilderMessaggioResponseDTO
         */
        public BuilderMessaggioResponseDTO setTesto(String testo) {
            this.testo = testo;
            return this;
        }

        /**
         * Verifica se i dati forniti sono validi per la costruzione di un oggetto MessaggioResponseDTO
         * @return true se i dati sono validi, altrimenti false
         */
        public boolean isValid() {
            return idDestinatario > 0 && testo != null;
        }

        /**
         * Costruisce un oggetto MessaggioResponseDTO con i dati forniti
         * @return Oggetto MessaggioResponseDTO
         * @throws BadRequestException se i dati forniti non sono validi
         */
        public MessaggioResponseDTO build() {
            if (isValid()) {
                return new MessaggioResponseDTO(idDestinatario, testo);
            }
            throw new BadRequestException("Messaggio non inviato");
        }
    }

}
