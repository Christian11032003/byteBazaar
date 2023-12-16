package com.bytebazaar.bytebazaar.repository;

import com.bytebazaar.bytebazaar.model.Feedback;
import com.bytebazaar.bytebazaar.model.Oggettocarrello;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeedbackRepository extends JpaRepository<Feedback,Integer>
{
    Optional<Feedback> findByOggettocarrello_Idoggettocarrello(int idoggettocarrello);
}
