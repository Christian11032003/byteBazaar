package com.bytebazaar.bytebazaar.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class Messaggio
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idmessaggio;

    @ManyToOne
    @JoinColumn(nullable = false,updatable = false,name = "idprodotto")
    private Prodotto prodotto;

    @ManyToOne
    @JoinColumn(nullable = false,updatable = false,name= "idutente")
    private Utente utente;

    @Column(nullable = false)
    private String testoMessaggio;

    @Column(nullable = false)
    private LocalDateTime dataOraArrivo;

    @Column(nullable = false)
    private boolean mittenteORdestinatario;

}
