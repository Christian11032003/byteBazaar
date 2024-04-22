package com.bytebazaar.bytebazaar.controller;

import com.bytebazaar.bytebazaar.dto.request.AddProductToCartRequest;
import com.bytebazaar.bytebazaar.dto.request.DeleteObjectFromCartRequest;
import com.bytebazaar.bytebazaar.dto.request.SubtractQuantityRequest;
import com.bytebazaar.bytebazaar.model.Utente;
import com.bytebazaar.bytebazaar.service.definition.OggettocarrelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OggettoCarrelloController
{
    @Autowired
    OggettocarrelloService serviceOggettocarrello;


    @PostMapping({"/venditore/aggiungiOggettoCarrello","/cliente/aggiungiOggettoCarrello"})
    public ResponseEntity<Void> aggiungiOggettoCarrello(UsernamePasswordAuthenticationToken token, @RequestBody AddProductToCartRequest request) {
        Utente u = (Utente)token.getPrincipal();
        boolean registratoOggettoCarrello = serviceOggettocarrello.aggiungiAlCarrello(u,request);
        if (registratoOggettoCarrello) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }


    @PostMapping({"/venditore/aggiungiOggettoCarrello","/cliente/sottraiQuantita"})
    public ResponseEntity<Void> sottraiquanità(UsernamePasswordAuthenticationToken token,@RequestBody SubtractQuantityRequest request) {
        Utente u = (Utente)token.getPrincipal();
        boolean sottraiQuantita = serviceOggettocarrello.sottraiQuantita(u,request);
        if (sottraiQuantita) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }


    @PostMapping({"/venditore/eliminaoggettocarrello","/cliente/eliminaoggettocarrello"})
    public ResponseEntity<Void> eliminaoggettocarrelloCliente(UsernamePasswordAuthenticationToken token,@RequestBody DeleteObjectFromCartRequest request) {
        Utente u = (Utente)token.getPrincipal();
        boolean eliminato = serviceOggettocarrello.eliminaoggettocarrello(u,request);
        if (eliminato) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }


}
