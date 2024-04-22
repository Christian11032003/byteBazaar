package com.bytebazaar.bytebazaar.dto.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class ProdottoDTO {
    private long idProdotto;
    private long idVenditore;
    private String nomeVenditore;
    private String immagineProdotto;
    private String nome;
    private String descrizione;
    private double prezzo;
    private int quantita;

    private ProdottoDTO(long idProdotto, long idVenditore, String nomeVenditore, String immagineProdotto, String nome, String descrizione, double prezzo, int quantita) {
        this.idProdotto = idProdotto;
        this.idVenditore = idVenditore;
        this.nomeVenditore = nomeVenditore;
        this.immagineProdotto = immagineProdotto;
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.quantita = quantita;
    }

    public static class Builder{
        private long idProdotto;
        private long idVenditore;
        private String nomeVenditore;
        private String immagineProdotto;
        private String nome;
        private String descrizione;
        private double prezzo;
        private int quantita;

        public Builder setIdProdotto(long idProdotto) {
            if(idProdotto<1)return null;
            this.idProdotto = idProdotto;
            return this;
        }

        public Builder setIdVenditore(long idVenditore) {
            this.idVenditore = idVenditore;
            return this;
        }

        public Builder setNomeVenditore(String nomeVenditore) {
            this.nomeVenditore = nomeVenditore;
            return this;
        }

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
            return idProdotto!=0&&
                    idVenditore!=0&&
                    nomeVenditore!=null&&
                    nome!=null;
        }

        public ProdottoDTO build(){
            if(isValid())return new ProdottoDTO(idProdotto,idVenditore,nomeVenditore,immagineProdotto,nome,descrizione,prezzo,quantita);
            else throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
