package com.bytebazaar.bytebazaar.dto.response;

import com.bytebazaar.bytebazaar.exception.messaggiException.BadRequestException;
import com.bytebazaar.bytebazaar.model.Condizione;
import lombok.Getter;

@Getter
public class ProdottoReponseDTO {


    private String nome;
    private String descrizione;
    private double prezzo;
    private int quantita;
    private Condizione condizione;

    public ProdottoReponseDTO(String nome, String descrizione, double prezzo, int quantita, Condizione condizione) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.quantita = quantita;
        this.condizione = condizione;
    }

    public static class BuilderProdottoDTO{

        private String nome;
        private String descrizione;
        private double prezzo;
        private int quantita;
        private Condizione condizione;

        public BuilderProdottoDTO setNome(String nome) {
            this.nome = nome;
            return this;
        }

        public BuilderProdottoDTO setDescrizione(String descrizione) {
            this.descrizione = descrizione;
            return this;
        }

        public BuilderProdottoDTO setPrezzo(double prezzo) {
            this.prezzo = prezzo;
            return this;
        }

        public BuilderProdottoDTO setQuantita(int quantita) {
            this.quantita = quantita;
            return this;
        }

        public BuilderProdottoDTO setCondizione(Condizione condizione)
        {
            this.condizione = condizione;
            return this;
        }

        public boolean isValid(){
            return prezzo>=0 && quantita>=0 && nome!=null;
        }

        public ProdottoReponseDTO build()
        {
            if(isValid())
            {
                return new ProdottoReponseDTO(nome,descrizione,prezzo,quantita,condizione);
            }

            throw new BadRequestException("Prodotto non registrato");

        }
    }
}
