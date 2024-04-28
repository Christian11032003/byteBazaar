package com.bytebazaar.bytebazaar.controller;

import com.bytebazaar.bytebazaar.facade.CarrelloFacade;
import com.bytebazaar.bytebazaar.model.Utente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CarrelloController
{

    private final CarrelloFacade carrelloFacade;

    @GetMapping({"/cliente/confermaCarrello","venditore/confermaCarrello"})
    public ResponseEntity<String> confermaCarrello(UsernamePasswordAuthenticationToken token) {
        Utente u = (Utente)token.getPrincipal();
        boolean bloccato = carrelloFacade.confermaCarrello(u);
        if (bloccato) return ResponseEntity.status(HttpStatus.OK).body("Carrello confermato con successo");
        else return ResponseEntity.badRequest().build();
    }

}
