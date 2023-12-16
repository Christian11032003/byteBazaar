package com.bytebazaar.bytebazaar.service.definition;

import com.bytebazaar.bytebazaar.dto.request.AggiungiFeedbackRequest;
import org.springframework.stereotype.Service;


public interface FeedbackService
{
    public boolean aggiungiFeedback(AggiungiFeedbackRequest request);
}
