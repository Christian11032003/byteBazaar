package com.bytebazaar.bytebazaar.controller;

import com.bytebazaar.bytebazaar.facade.CarrelloFacade;
import com.bytebazaar.bytebazaar.model.Utente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CarrelloController
{

    private final CarrelloFacade facade;

    @GetMapping({"/venditore/confermaCarrello","/cliente/confermaCarrello"})
    public ResponseEntity<Void> confermaCarrello(UsernamePasswordAuthenticationToken token) {
        Utente u = (Utente)token.getPrincipal();
        boolean bloccato = facade.confermaCarrello(u);
        if (bloccato) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }

}
