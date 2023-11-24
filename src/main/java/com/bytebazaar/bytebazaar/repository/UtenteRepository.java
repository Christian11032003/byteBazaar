package com.bytebazaar.bytebazaar.repository;

import com.bytebazaar.bytebazaar.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtenteRepository extends JpaRepository<Utente, Integer>
{
    Optional<Utente> findByUsernameAndPassword(String username, String password);
}
