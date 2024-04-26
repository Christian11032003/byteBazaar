package com.bytebazaar.bytebazaar.repository;

import com.bytebazaar.bytebazaar.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FeedbackRepository extends JpaRepository<Feedback,Integer>
{

    List<Feedback> findAllByOggettocarrello_Prodotto_IdProdotto(int idprodotto);

    List<Feedback> findAllByOggettocarrello_Carrello_Utente_Idutente(int idUtente);
}
