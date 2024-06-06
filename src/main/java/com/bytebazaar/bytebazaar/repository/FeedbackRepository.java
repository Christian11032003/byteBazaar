package com.bytebazaar.bytebazaar.repository;

import com.bytebazaar.bytebazaar.model.Feedback;
import com.bytebazaar.bytebazaar.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

    /**
     * Metodo per trovare tutti i feedback associati a un prodotto tramite l'ID del prodotto.
     * Restituisce una lista di Feedback.
     *
     * @param idprodotto l'ID del prodotto di cui trovare i feedback
     * @return una lista di Feedback associati al prodotto specificato
     */
    public List<Feedback> findAllByOggettocarrello_Prodotto_Id(int idprodotto);

    /**
     * Metodo per trovare tutti i feedback associati a un carrello di un utente tramite l'ID dell'utente.
     * Restituisce una lista di Feedback.
     *
     * @param idUtente l'ID dell'utente di cui trovare i feedback associati al carrello
     * @return una lista di Feedback associati al carrello dell'utente specificato
     */
    public List<Feedback> findAllByOggettocarrello_Carrello_Utente_Id(int idUtente);

    /**
     * Metodo per trovare un feedback specifico tramite il suo ID.
     * Restituisce un Feedback.
     *
     * @param idFeedback l'ID del feedback da trovare
     * @return il Feedback associato all'ID specificato
     */
    public Feedback findByIdfeedback(int idFeedback);

    /**
     * Metodo per trovare tutti i feedback associati a un carrello di un utente tramite l'entità Utente.
     * Restituisce una lista di Feedback.
     *
     * @param u l'utente di cui trovare i feedback associati al carrello
     * @return una lista di Feedback associati al carrello dell'utente specificato
     */
    public List<Feedback> findAllByOggettocarrello_Carrello_Utente(Utente u);
}
