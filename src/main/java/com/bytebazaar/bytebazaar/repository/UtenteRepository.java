package com.bytebazaar.bytebazaar.repository;

import com.bytebazaar.bytebazaar.model.Ruolo;
import com.bytebazaar.bytebazaar.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UtenteRepository extends JpaRepository<Utente, Integer>
{

    Optional<Utente> findByIdutente(int idutente);

    Optional<Utente> findByUsernameAndPassword(String username,String password);

    List<Utente> findAllByRuolo(Ruolo ruolo);

    Optional<Utente> findByUsername(String username);
}
