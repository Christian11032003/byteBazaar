package com.bytebazaar.bytebazaar.dto.response;

import com.bytebazaar.bytebazaar.exception.messaggiException.BadRequestException;
import com.bytebazaar.bytebazaar.model.Oggettocarrello;
import lombok.Getter;

@Getter
public class OggettoCarrelloReponseDTO
{

    private int id;
    private int quantita;

    public OggettoCarrelloReponseDTO(int id, int quantita) {
        this.id = id;
        this.quantita = quantita;
    }

    public static class BuilderOggettoCarrelloDTO
    {
        private int id;
        private int quantita;


        public BuilderOggettoCarrelloDTO setId(int id) {
            this.id = id;
            return this;
        }

        public BuilderOggettoCarrelloDTO setQuantita(int quantita) {
            this.quantita = quantita;
            return this;
        }

        private boolean isValid(){
            return quantita >=0 && id > 0;
        }

        public OggettoCarrelloReponseDTO build()
        {
            if(isValid())
            {
                return new OggettoCarrelloReponseDTO(id,quantita);
            }

            throw new BadRequestException("Impossibile aggiungere al carrello");
        }






    }
}
