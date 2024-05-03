package com.bytebazaar.bytebazaar.service.definition;

import com.bytebazaar.bytebazaar.model.Feedback;

import java.util.List;


public interface FeedbackService
{

    public List<Feedback> getByIdProdotto(int idProdotto);

    public Feedback getByIdFeedback(int idFeedback);

    public void salva(Feedback f);
}
