package com.bytebazaar.bytebazaar.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
//entità Richiesta con i relativi campi e le relazioni
@Data
@NoArgsConstructor
@Entity
public class Richiesta
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(nullable = false,updatable = false,name = "idutente")
    private Utente utente;

    @Enumerated(EnumType.STRING)
    public Stato stato;

}
