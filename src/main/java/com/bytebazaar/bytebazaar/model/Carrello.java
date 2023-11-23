package com.bytebazaar.bytebazaar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Carrello
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCarrello;

    @ManyToOne
    @JoinColumn(nullable = false,updatable = false,name = "idUtente")
    private Utente utente;

    @Column(nullable = false)
    private LocalDateTime dataAcquisto;

    @JsonIgnore
    @OneToMany(mappedBy = "carrello")
    private List<Oggettocarrello> oggettoCarrello;



}
