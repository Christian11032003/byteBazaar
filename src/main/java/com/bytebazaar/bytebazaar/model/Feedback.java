package com.bytebazaar.bytebazaar.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

//entità Feedback con i relativi campi e le relazioni
@Data
@NoArgsConstructor
@Entity
public class Feedback
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idfeedback;

    @ManyToOne
    @JoinColumn(nullable = false,updatable = false,name = "idoggettocarrello")
    private Oggettocarrello oggettocarrello;

    @Column(nullable = false)
    private String descrizione;

    @Column(nullable = false)
    @Range(min = 1, max = 5,message = "La valutazione deve essere compresa tra 1 e 5 stelle")
    private int valutazione;


}
