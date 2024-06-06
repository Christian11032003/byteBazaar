package com.bytebazaar.bytebazaar.service.implementation;

import com.bytebazaar.bytebazaar.model.*;
import com.bytebazaar.bytebazaar.repository.*;
import com.bytebazaar.bytebazaar.service.definition.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
// La classe implementa l'interfaccia FeedbackService, fornendo l'implementazione dei metodi definiti nell'interfaccia.
public class FeedbackServiceImpl implements FeedbackService {

    // Il FeedbackRepository è una dipendenza di questo servizio, utilizzata per le operazioni di accesso ai dati relative alle entità 'Feedback'.
    private final FeedbackRepository feedbackRepo;


    /**
     * Recupera una lista di feedback per un dato idProdotto.
     *
     * @param idProdotto l'ID del prodotto di cui ottenere i feedback
     * @return una lista di oggetti Feedback associati al prodotto specificato
     */
    @Override
    public List<Feedback> getByIdProdotto(int idProdotto) {
        return feedbackRepo.findAllByOggettocarrello_Prodotto_Id(idProdotto);
    }

    /**
     * Recupera un feedback specifico per un dato idFeedback.
     *
     * @param idFeedback l'ID del feedback da recuperare
     * @return l'oggetto Feedback corrispondente all'ID specificato
     */
    @Override
    public Feedback getByIdFeedback(int idFeedback) {
        return feedbackRepo.findByIdfeedback(idFeedback);
    }

    /**
     * Salva un'istanza di Feedback nel repository.
     *
     * @param f l'istanza di Feedback da salvare
     */
    @Override
    public void salva(Feedback f) {
        feedbackRepo.save(f);
    }
}


