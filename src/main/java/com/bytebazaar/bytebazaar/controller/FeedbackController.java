package com.bytebazaar.bytebazaar.controller;

import com.bytebazaar.bytebazaar.dto.request.AddFeedbackRequest;
import com.bytebazaar.bytebazaar.model.Utente;
import com.bytebazaar.bytebazaar.service.definition.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedbackController
{
    @Autowired
    FeedbackService serviceFeedback;

    @PostMapping({"/clienteVenditore/mandaFeedback","/cliente/mandaFeedback"})
    public ResponseEntity<Void> mandaMessaggio(UsernamePasswordAuthenticationToken token, @RequestBody AddFeedbackRequest request) {
        Utente u = (Utente)token.getPrincipal();
        boolean addFeedback = serviceFeedback.aggiungiFeedback(u,request);
        if (addFeedback) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }




}
