package com.bytebazaar.bytebazaar.repository;

import com.bytebazaar.bytebazaar.model.Ruolo;
import com.bytebazaar.bytebazaar.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtenteRepository extends JpaRepository<Utente, Integer>
{
    Optional<Utente> findByUsernameAndPasswordAndRuolo(String username, String password, Ruolo ruolo);

    Optional<Utente> findByIdutente(int idutente);
}
