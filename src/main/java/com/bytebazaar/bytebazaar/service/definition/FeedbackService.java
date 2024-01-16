package com.bytebazaar.bytebazaar.service.definition;

import com.bytebazaar.bytebazaar.dto.request.AggiungiFeedbackRequest;
import com.bytebazaar.bytebazaar.exception.messaggiException.BadRequestException;
import com.bytebazaar.bytebazaar.exception.messaggiException.NotFoundException;
import com.bytebazaar.bytebazaar.model.Utente;
import org.springframework.stereotype.Service;


public interface FeedbackService
{
    public boolean aggiungiFeedback(Utente u, AggiungiFeedbackRequest request);
}
