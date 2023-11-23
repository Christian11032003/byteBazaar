package com.bytebazaar.bytebazaar.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Feedback
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFeedback;

    @ManyToOne
    @JoinColumn(nullable = false,updatable = false,name = "idOggettocarrello")
    private Oggettocarrello oggettoCarrello;


}
