package com.bytebazaar.bytebazaar.controller;

import com.bytebazaar.bytebazaar.dto.request.AddFeedbackRequestDTO;
import com.bytebazaar.bytebazaar.dto.request.ModifyFeedbackRequestDTO;
import com.bytebazaar.bytebazaar.facade.FeedbackFacade;
import com.bytebazaar.bytebazaar.model.Utente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<String> mandaFeedback(UsernamePasswordAuthenticationToken token, @RequestBody AddFeedbackRequestDTO request) {
        Utente u = (Utente)token.getPrincipal();
        boolean addFeedback = feedbackFacade.aggiungiFeedback(u,request);
        if (addFeedback) return ResponseEntity.status(HttpStatus.OK).body("Feedback aggiunto con successo");
        else return ResponseEntity.badRequest().build();
    }


    @PostMapping({"/venditore/modificaFeedback","/cliente/modificaFeedback"})
    public ResponseEntity<String> modificaFeedback(@RequestBody ModifyFeedbackRequestDTO request) {

        boolean addFeedback = feedbackFacade.modificaFeedback(request);
        if (addFeedback) return ResponseEntity.status(HttpStatus.OK).body("Feedback aggiunto con successo");
        else return ResponseEntity.badRequest().build();
    }




}
