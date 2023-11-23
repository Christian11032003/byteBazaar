package com.bytebazaar.bytebazaar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Oggettocarrello
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOggettocarrello;

    @ManyToOne
    @JoinColumn(nullable = false,updatable = false,name = "idProdotto")
    private Prodotto prodotto;

    @ManyToOne
    @JoinColumn(nullable = false,updatable = false,name = "idCarrello")
    private Carrello carrello;

    @Column(nullable = false)
    private int quantita;

    @JsonIgnore
    @OneToMany(mappedBy = "oggettoCarrello")
    private List<Feedback> feedback;
}
