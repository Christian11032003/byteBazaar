package com.bytebazaar.bytebazaar.controller;

import com.bytebazaar.bytebazaar.dto.request.feedback.AddFeedbackRequestDTO;
import com.bytebazaar.bytebazaar.facade.FeedbackFacade;
import com.bytebazaar.bytebazaar.model.Utente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FeedbackController
{

    private final FeedbackFacade feedbackFacade;

    @PostMapping({"/venditore/mandaFeedback","/cliente/mandaFeedback"})
    public ResponseEntity<Void> mandaMessaggio(UsernamePasswordAuthenticationToken token, @RequestBody AddFeedbackRequestDTO request) {
        Utente u = (Utente)token.getPrincipal();
        boolean addFeedback = feedbackFacade.aggiungiFeedback(u,request);
        if (addFeedback) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }




}
