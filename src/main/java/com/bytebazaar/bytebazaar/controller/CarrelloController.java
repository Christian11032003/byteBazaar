package com.bytebazaar.bytebazaar.controller;

import com.bytebazaar.bytebazaar.dto.request.BannedOrUnBannedAdminRequest;
import com.bytebazaar.bytebazaar.dto.request.LoginRequest;
import com.bytebazaar.bytebazaar.model.Utente;
import com.bytebazaar.bytebazaar.service.definition.CarrelloService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarrelloController
{
    @Autowired
    CarrelloService serviceCarrello;

    @GetMapping("/cliente/confermaCarrello")
    public ResponseEntity<Void> confermaCarrelloCliente(UsernamePasswordAuthenticationToken token) {
        Utente u = (Utente)token.getPrincipal();
        boolean bloccato = serviceCarrello.confermaCarrello(u);
        if (bloccato) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }

    @GetMapping("/venditore/confermaCarrello")
    public ResponseEntity<Void> confermaCarrelloClienteVenditore(UsernamePasswordAuthenticationToken token) {
        Utente u = (Utente)token.getPrincipal();
        boolean bloccato = serviceCarrello.confermaCarrello(u);
        if (bloccato) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }
}
