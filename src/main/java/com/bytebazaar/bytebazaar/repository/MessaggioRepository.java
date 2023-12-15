package com.bytebazaar.bytebazaar.repository;

import com.bytebazaar.bytebazaar.model.Messaggio;
import com.bytebazaar.bytebazaar.model.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MessaggioRepository extends JpaRepository<Messaggio,Integer>
{

    Optional<Messaggio> findMessaggioByUtente_IdutenteAndProdotto_IdProdotto(int idutente,int idprodotto);


}
