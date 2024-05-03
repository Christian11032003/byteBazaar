package com.bytebazaar.bytebazaar.repository;

import com.bytebazaar.bytebazaar.model.Messaggio;
import com.bytebazaar.bytebazaar.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MessaggioRepository extends JpaRepository<Messaggio,Integer>
{
    public List<Messaggio> findAllByUtente_Id(int idUtente);

    public List<Messaggio> findAllByUtente(Utente u);
}
