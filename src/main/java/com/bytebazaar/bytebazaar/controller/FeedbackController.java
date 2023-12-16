package com.bytebazaar.bytebazaar.controller;

import com.bytebazaar.bytebazaar.dto.request.AggiungiFeedbackRequest;
import com.bytebazaar.bytebazaar.service.definition.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedbackController
{
    @Autowired
    FeedbackService serviceFeedback;

    @PostMapping("/mandaFeedback")
    public ResponseEntity<Void> mandaMessaggio(@RequestBody AggiungiFeedbackRequest request) {
        boolean addFeedback = serviceFeedback.aggiungiFeedback(request);
        if (addFeedback) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }
}
