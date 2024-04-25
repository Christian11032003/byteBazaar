package com.bytebazaar.bytebazaar.controller;

import com.bytebazaar.bytebazaar.dto.request.messaggio.SendMessageRequest;
import com.bytebazaar.bytebazaar.facade.MessaggioFacade;
import com.bytebazaar.bytebazaar.model.Utente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessaggioController
{

    private final MessaggioFacade messaggioFacade;

    @PostMapping({"/venditore/mandaMessaggio","/cliente/mandaMessaggio"})
    public ResponseEntity<Void> mandaMessaggio(UsernamePasswordAuthenticationToken token, @RequestBody SendMessageRequest request) {
        Utente u = (Utente)token.getPrincipal();
        boolean sendMessaggio = messaggioFacade.mandaMessaggio(u,request);
        if (sendMessaggio) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }
}
