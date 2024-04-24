package com.bytebazaar.bytebazaar.service.definition;

import com.bytebazaar.bytebazaar.dto.request.AddFeedbackRequest;
import com.bytebazaar.bytebazaar.model.Feedback;
import com.bytebazaar.bytebazaar.model.Utente;

import java.util.List;


public interface FeedbackService
{

    public List<Feedback> getByIdProdotto(int idProdotto);

    public void salva(Feedback f);
}
