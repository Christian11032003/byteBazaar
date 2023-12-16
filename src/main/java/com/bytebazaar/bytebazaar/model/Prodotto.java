package com.bytebazaar.bytebazaar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Prodotto
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProdotto;

    @ManyToOne
    @JoinColumn(nullable = false,updatable = false,name = "idutente")
    private Utente utente;

    private String immagineProdotto;

    @Column(nullable = false)
    private String nome;

    private String descrizione;
    @Column(nullable = false)
    private double prezzo;

    @Column(nullable = false)
    private int quantita;


    @JsonIgnore
    @OneToMany(mappedBy = "prodotto")
    private List<Oggettocarrello> oggettocarrello;





}
