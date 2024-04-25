package com.bytebazaar.bytebazaar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class OggettoCarrello
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idoggettocarrello;

    @ManyToOne
    @JoinColumn(nullable = false,updatable = false,name = "idprodotto")
    private Prodotto prodotto;

    @ManyToOne
    @JoinColumn(nullable = false,updatable = false,name = "idcarrello")
    private Carrello carrello;

    @Column(nullable = false)
    private int quantita;

    @JsonIgnore
    @OneToMany(mappedBy = "oggettocarrello",fetch = FetchType.EAGER)
    private List<Feedback> feedback;
}
