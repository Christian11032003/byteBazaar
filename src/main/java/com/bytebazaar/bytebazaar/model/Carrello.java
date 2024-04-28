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
    private int id;

    @ManyToOne
    @JoinColumn(nullable = false,updatable = false,name = "idutente")
    private Utente utente;

    private LocalDateTime dataacquisto;

    @JsonIgnore
    @OneToMany(mappedBy = "carrello",fetch = FetchType.EAGER)
    private List<Oggettocarrello> oggettocarrello;



}
