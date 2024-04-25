package com.bytebazaar.bytebazaar.repository;

import com.bytebazaar.bytebazaar.model.OggettoCarrello;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OggettocarrelloRepository extends JpaRepository<OggettoCarrello,Integer>
{

    Optional<OggettoCarrello> findByCarrello_IdcarrelloAndProdotto_IdProdotto(int idcarrello, int idprodotto);

    Optional<OggettoCarrello> findById(int idoggettocarrello);
}
