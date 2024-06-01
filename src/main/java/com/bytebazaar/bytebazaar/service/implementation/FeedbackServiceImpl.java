package com.bytebazaar.bytebazaar.service.implementation;

import com.bytebazaar.bytebazaar.model.*;
import com.bytebazaar.bytebazaar.repository.*;
import com.bytebazaar.bytebazaar.service.definition.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService
{



    private final FeedbackRepository feedbackRepo;


    @Override
    public List<Feedback> getByIdProdotto(int idProdotto) {
        return feedbackRepo.findAllByOggettocarrello_Prodotto_Id(idProdotto);
    }

    @Override
    public Feedback getByIdFeedback(int idFeedback) {
        return feedbackRepo.findByIdfeedback(idFeedback);
    }

    @Override
    public void salva(Feedback f) {
        feedbackRepo.save(f);
    }
}


