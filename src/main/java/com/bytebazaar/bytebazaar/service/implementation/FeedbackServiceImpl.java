package com.bytebazaar.bytebazaar.service.implementation;

import com.bytebazaar.bytebazaar.model.*;
import com.bytebazaar.bytebazaar.repository.*;
import com.bytebazaar.bytebazaar.service.definition.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService
{


    @Autowired
    private FeedbackRepository feedbackRepo;


    @Override
    public List<Feedback> getByIdProdotto(int idProdotto) {
        return feedbackRepo.findAllByOggettocarrello_Prodotto_Id(idProdotto);
    }

    @Override
    public void salva(Feedback f) {
        feedbackRepo.save(f);
    }
}


