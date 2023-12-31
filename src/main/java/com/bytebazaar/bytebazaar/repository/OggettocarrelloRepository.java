package com.bytebazaar.bytebazaar.repository;

import com.bytebazaar.bytebazaar.model.Oggettocarrello;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OggettocarrelloRepository extends JpaRepository<Oggettocarrello,Integer>
{

    Optional<Oggettocarrello> findByCarrello_IdcarrelloAndProdotto_IdProdotto(int idcarrello, int idprodotto);

    Optional<Oggettocarrello> findById(int idoggettocarrello);
}
