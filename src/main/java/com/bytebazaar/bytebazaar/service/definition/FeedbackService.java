package com.bytebazaar.bytebazaar.service.definition;

import com.bytebazaar.bytebazaar.model.Feedback;

import java.util.List;


// Interfaccia per il servizio relativo alla gestione dei feedback
public interface FeedbackService {
    /**
     * Metodo per ottenere tutti i feedback associati a un prodotto tramite l'ID del prodotto.
     *
     * @param idProdotto l'ID del prodotto di cui ottenere i feedback
     * @return una lista di Feedback associati al prodotto specificato
     */
    public List<Feedback> getByIdProdotto(int idProdotto);

    /**
     * Metodo per ottenere un feedback specifico tramite l'ID del feedback.
     *
     * @param idFeedback l'ID del feedback da ottenere
     * @return il Feedback associato all'ID specificato
     */
    public Feedback getByIdFeedback(int idFeedback);

    /**
     * Metodo per salvare un feedback nel database.
     *
     * @param f il feedback da salvare
     */
    public void salva(Feedback f);
}
