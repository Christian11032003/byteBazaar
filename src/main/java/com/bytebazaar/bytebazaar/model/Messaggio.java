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
    private int idMessaggio;

    @ManyToOne
    @JoinColumn(nullable = false,updatable = false,name = "idProdotto")
    private Prodotto prodotto;

    @Column(nullable = false)
    private String testoMessaggio;

    @Column(nullable = false)
    private LocalDateTime dataOraArrivo;

    @Column(nullable = false)
    private boolean mittenteORdestinatario;

}
