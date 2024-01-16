package com.bytebazaar.bytebazaar.controller;

import com.bytebazaar.bytebazaar.dto.request.LoginRequest;
import com.bytebazaar.bytebazaar.dto.request.MandaMessaggioRequest;
import com.bytebazaar.bytebazaar.model.Utente;
import com.bytebazaar.bytebazaar.service.definition.CarrelloService;
import com.bytebazaar.bytebazaar.service.definition.MessaggioService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessaggioController
{
    @Autowired
    MessaggioService serviceMessaggio;

    @PostMapping("/mandaMessaggio")
    public ResponseEntity<Void> mandaMessaggio(UsernamePasswordAuthenticationToken token, @RequestBody MandaMessaggioRequest request) {
        Utente u = (Utente)token.getPrincipal();
        boolean sendMessaggio = serviceMessaggio.mandaMessaggio(u,request);
        if (sendMessaggio) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }
}
