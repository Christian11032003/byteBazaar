package com.bytebazaar.bytebazaar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Utente implements UserDetails
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

    private boolean bloccato = false;

    @JsonIgnore
    @OneToMany(mappedBy = "utente")
    private List<Prodotto> prodotto;

    @JsonIgnore
    @OneToMany(mappedBy = "utente")
    private List<Carrello> carrello;

    @JsonIgnore
    @OneToMany(mappedBy = "utente")
    private List<Richiesta> richiesta;

    @JsonIgnore
    @OneToMany(mappedBy = "utente")
    private List<Messaggio> messaggio;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //il testo passato al costruttore del SimpleGrantedAuthority vuole SEMPRE la scritta ROLE_ prima del nome del ruolo
        SimpleGrantedAuthority sga=new SimpleGrantedAuthority("ROLE_"+ruolo);
        return List.of(sga);
    }

    //metodo per visualizzare lo username dell'utente (nel nostro caso utilizziamo l'email come username)
    @Override
    public String getUsername() {
        return username;
    }

    //metodo utilizzato (di solito) per controllare se l'account non è "scaduto"
    //possibili implementazioni possono essere per account temporarei oppure per un controllo
    //della login (se non fai la login da almeno 6 mesi "disattivo" l'account
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    //metodo utilizzato per controllare che l'account sia ancora attivo e che non sia stato
    //bloccato da un admin
    @Override
    public boolean isAccountNonLocked() {
        return !bloccato;
    }
    //alcuni applicativi hanno l'obbligo di rinnovare la password ogni tot tempo oppure
    //gli account durano un periodo prestabilito e non saranno rinnovabili
    //metodo per controllare che sia ancora tutto valido
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    //metodo per controllare se l'account è stato attivato (utilizzato soprattutto dai
    //siti la cui registrazione deve essere approvata)
    @Override
    public boolean isEnabled() {
        return true;
    }


}
