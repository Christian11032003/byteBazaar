package com.bytebazaar.bytebazaar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Utente
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idutente;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;
    @Column(unique = true,nullable = false,name = "email")
    private String email;

    @Column(unique = true,nullable = false,name = "username")
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Ruolo ruolo;

    @JsonIgnore
    @OneToMany(mappedBy = "utente")
    private List<Prodotto> prodotto;

    @JsonIgnore
    @OneToMany(mappedBy = "utente")
    private List<Carrello> carrello;

}
