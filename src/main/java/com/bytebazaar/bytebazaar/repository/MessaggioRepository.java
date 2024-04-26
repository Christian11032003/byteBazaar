package com.bytebazaar.bytebazaar.repository;

import com.bytebazaar.bytebazaar.model.Messaggio;
import com.bytebazaar.bytebazaar.model.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MessaggioRepository extends JpaRepository<Messaggio,Integer>
{
    public List<Messaggio> findAllByUtente_Idutente(int idUtente);
}
