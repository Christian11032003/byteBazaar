package com.bytebazaar.bytebazaar.service.definition;

import com.bytebazaar.bytebazaar.dto.request.AddFeedbackRequest;
import com.bytebazaar.bytebazaar.model.Utente;


public interface FeedbackService
{
    public boolean aggiungiFeedback(Utente u, AddFeedbackRequest request);
}
