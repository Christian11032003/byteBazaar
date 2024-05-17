package com.bytebazaar.bytebazaar.dto.response;

import com.bytebazaar.bytebazaar.exception.messaggiException.BadRequestException;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class ProdottoDTO {

    private String immagineProdotto;
    private String nome;
    private String descrizione;
    private double prezzo;
    private int quantita;

    private ProdottoDTO(String immagineProdotto, String nome, String descrizione, double prezzo, int quantita) {
        this.immagineProdotto = immagineProdotto;
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.quantita = quantita;
    }

    public static class Builder{

        private String immagineProdotto;
        private String nome;
        private String descrizione;
        private double prezzo;
        private int quantita;


        public Builder setImmagineProdotto(String immagineProdotto) {
            this.immagineProdotto = immagineProdotto;
            return this;
        }

        public Builder setNome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder setDescrizione(String descrizione) {
            this.descrizione = descrizione;
            return this;
        }

        public Builder setPrezzo(double prezzo) {
            this.prezzo = prezzo;
            return this;
        }

        public Builder setQuantita(int quantita) {
            this.quantita = quantita;
            return this;
        }

        private boolean isValid(){
            return prezzo>=0 && quantita>=0 && nome!=null;
        }

        public ProdottoDTO build(){
            if(isValid())
            {
                return new ProdottoDTO(immagineProdotto,nome,descrizione,prezzo,quantita);
            }

            throw new BadRequestException("Prodotto non registrato");


        }
    }
}
