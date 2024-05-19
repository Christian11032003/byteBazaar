package com.bytebazaar.bytebazaar.dto.response;


import com.bytebazaar.bytebazaar.exception.messaggiException.BadRequestException;
import lombok.Getter;

@Getter
public class MessaggioResponseDTO
{
    private int idDestinatario;
    private String testo;


    public MessaggioResponseDTO(int idDestinatario, String testo) {
        this.idDestinatario = idDestinatario;
        this.testo = testo;
    }

    public static class BuilderMessaggioResponseDTO
    {
        private int idDestinatario;
        private String testo;


        public BuilderMessaggioResponseDTO setIdDestinatario(int idDestinatario) {
            this.idDestinatario = idDestinatario;
            return this;
        }

        public BuilderMessaggioResponseDTO setTesto(String testo) {
            this.testo = testo;
            return this;
        }

        public boolean isValid()
        {
            return idDestinatario > 0  && testo != null;


        }

        public MessaggioResponseDTO build()
        {
            if(isValid())
            {
                return new MessaggioResponseDTO(idDestinatario,testo);
            }

            throw new BadRequestException("Messaggio non inviato");
        }


    }

}
