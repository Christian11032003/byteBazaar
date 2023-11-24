package com.bytebazaar.bytebazaar.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Richiesta
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idrichiesta;

    @ManyToOne
    @JoinColumn(nullable = false,updatable = false,name = "idutente")
    private Utente utente;


    public boolean accettato;

}
