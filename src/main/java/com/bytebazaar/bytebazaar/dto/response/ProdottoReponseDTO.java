package com.bytebazaar.bytebazaar.dto.response;

import com.bytebazaar.bytebazaar.exception.messaggiException.BadRequestException;
import com.bytebazaar.bytebazaar.model.Condizione;
import lombok.Getter;

//DTO per la response
@Getter
public class ProdottoReponseDTO {

    // Campo privato che rappresenta l'ID del venditore associato al prodotto
    private int idVenditore;

    // Campo privato che rappresenta il nome del prodotto
    private String nome;

    // Campo privato che rappresenta la descrizione del prodotto
    private String descrizione;

    // Campo privato che rappresenta il prezzo del prodotto
    private double prezzo;

    // Campo privato che rappresenta la quantità disponibile del prodotto
    private int quantita;

    // Campo privato che rappresenta la condizione del prodotto (es. nuovo, usato, ricondizionato, etc.)
    private Condizione condizione;

    // Costruttore che prende tutti i campi eccetto idVenditore
    public ProdottoReponseDTO(String nome, String descrizione, double prezzo, int quantita, Condizione condizione) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.quantita = quantita;
        this.condizione = condizione;
    }

    // Costruttore che prende tutti i campi inclusivo di idVenditore
    public ProdottoReponseDTO(int idVenditore, String nome, String descrizione, double prezzo, int quantita, Condizione condizione) {
        this.idVenditore = idVenditore;
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.quantita = quantita;
        this.condizione = condizione;
    }

    // Classe Builder per creare oggetti ProdottoReponseDTO in modo flessibile
    public static class BuilderProdottoDTO{

        // Campi privati del builder corrispondenti ai campi del prodotto
        private int idVenditore;
        private String nome;
        private String descrizione;
        private double prezzo;
        private int quantita;
        private Condizione condizione;

        // Metodo setter per impostare idVenditore e ritornare l'istanza del builder
        public BuilderProdottoDTO setIdVenditore(int idVenditore) {
            this.idVenditore = idVenditore;
            return this;
        }

        // Metodo setter per impostare il nome e ritornare l'istanza del builder
        public BuilderProdottoDTO setNome(String nome) {
            this.nome = nome;
            return this;
        }

        // Metodo setter per impostare la descrizione e ritornare l'istanza del builder
        public BuilderProdottoDTO setDescrizione(String descrizione) {
            this.descrizione = descrizione;
            return this;
        }

        // Metodo setter per impostare il prezzo e ritornare l'istanza del builder
        public BuilderProdottoDTO setPrezzo(double prezzo) {
            this.prezzo = prezzo;
            return this;
        }

        // Metodo setter per impostare la quantità e ritornare l'istanza del builder
        public BuilderProdottoDTO setQuantita(int quantita) {
            this.quantita = quantita;
            return this;
        }

        // Metodo setter per impostare la condizione e ritornare l'istanza del builder
        public BuilderProdottoDTO setCondizione(Condizione condizione) {
            this.condizione = condizione;
            return this;
        }

        // Metodo per verificare se i campi sono validi
        public boolean isValid() {
            return prezzo >= 0 && quantita >= 0 && nome != null;
        }

        // Metodo per costruire l'oggetto ProdottoReponseDTO
        public ProdottoReponseDTO build() {
            // Verifica se i campi sono validi e lancia un'eccezione se non lo sono
            if (isValid()) {
                return new ProdottoReponseDTO(nome, descrizione, prezzo, quantita, condizione);
            }
            // Altrimenti, lancia un'eccezione BadRequestException
            throw new BadRequestException("Prodotto non registrato");
        }

        // Metodo per costruire l'oggetto ProdottoReponseDTO con idVenditore
        public ProdottoReponseDTO build2() {
            // Verifica se i campi sono validi e se idVenditore è maggiore di zero, altrimenti lancia un'eccezione BadRequestException
            if (isValid() && idVenditore > 0) {
                return new ProdottoReponseDTO(idVenditore, nome, descrizione, prezzo, quantita, condizione);
            }
            // Altrimenti, lancia un'eccezione BadRequestException
            throw new BadRequestException("Prodotto non registrato");
        }
    }
}
