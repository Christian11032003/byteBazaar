package com.bytebazaar.bytebazaar.repository;

import com.bytebazaar.bytebazaar.model.Oggettocarrello;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OggettocarrelloRepository extends JpaRepository<Oggettocarrello,Integer>
{

    Optional<Oggettocarrello> findByCarrello_IdAndProdotto_Id(int idcarrello, int idprodotto);

    Optional<Oggettocarrello> findById(int idoggettocarrello);
}
