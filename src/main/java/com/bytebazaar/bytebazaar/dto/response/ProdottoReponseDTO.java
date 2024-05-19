package com.bytebazaar.bytebazaar.dto.response;

import com.bytebazaar.bytebazaar.exception.messaggiException.BadRequestException;
import lombok.Getter;

@Getter
public class ProdottoReponseDTO {

    private String immagineProdotto;
    private String nome;
    private String descrizione;
    private double prezzo;
    private int quantita;

    private ProdottoReponseDTO(String immagineProdotto, String nome, String descrizione, double prezzo, int quantita) {
        this.immagineProdotto = immagineProdotto;
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.quantita = quantita;
    }

    public static class BuilderProdottoDTO{

        private String immagineProdotto;
        private String nome;
        private String descrizione;
        private double prezzo;
        private int quantita;


        public BuilderProdottoDTO setImmagineProdotto(String immagineProdotto) {
            this.immagineProdotto = immagineProdotto;
            return this;
        }

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

        private boolean isValid(){
            return prezzo>=0 && quantita>=0 && nome!=null;
        }

        public ProdottoReponseDTO build()
        {
            if(isValid())
            {
                return new ProdottoReponseDTO(immagineProdotto,nome,descrizione,prezzo,quantita);
            }

            throw new BadRequestException("Prodotto non registrato");

        }
    }
}
