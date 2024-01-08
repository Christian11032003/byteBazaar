package com.bytebazaar.bytebazaar.repository;

import com.bytebazaar.bytebazaar.model.Richiesta;
import com.bytebazaar.bytebazaar.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RichiestaRepository extends JpaRepository<Richiesta,Integer>
{
    Optional<Richiesta> findByIdrichiesta(int idrichiesta);

    Optional<Richiesta> findByUtente_Username(String username);
}
