package com.bytebazaar.bytebazaar.dto.response;

import com.bytebazaar.bytebazaar.exception.messaggiException.BadRequestException;
import lombok.Getter;

@Getter
public class FeedbackResponseDTO
{
    private int idProdotto;

    private String descrizione;

    private int valutazione;

    public FeedbackResponseDTO(int idProdotto, String descrizione, int valutazione) {
        this.idProdotto = idProdotto;
        this.descrizione = descrizione;
        this.valutazione = valutazione;
    }

    public static class BuilderFeedbackResponseDTO
    {
        private int idProdotto;

        private String descrizione;

        private int valutazione;

        public BuilderFeedbackResponseDTO setIdProdotto(int idProdotto) {
            this.idProdotto = idProdotto;
            return this;
        }

        public BuilderFeedbackResponseDTO setDescrizione(String descrizione) {
            this.descrizione = descrizione;
            return this;
        }

        public BuilderFeedbackResponseDTO setValutazione(int valutazione) {
            this.valutazione = valutazione;
            return this;
        }


        private boolean isValid()
        {
            return idProdotto > 0 && descrizione != null && valutazione > 0;
        }

        public FeedbackResponseDTO build()
        {
            if (isValid())
            {
                return new FeedbackResponseDTO(idProdotto,descrizione,valutazione);
            }

            throw new BadRequestException("Impossibile aggiungere il feedback");
        }





    }
}
