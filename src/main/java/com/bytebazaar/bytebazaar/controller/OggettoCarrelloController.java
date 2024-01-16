package com.bytebazaar.bytebazaar.controller;

import com.bytebazaar.bytebazaar.dto.request.AggiungiProdottoInCarrelloRequest;
import com.bytebazaar.bytebazaar.dto.request.EliminaOggettoCarrelloRequest;
import com.bytebazaar.bytebazaar.dto.request.SottraiQuantitaRequest;
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


    @PostMapping("/cliente/aggiungiOggettoCarrello")
    public ResponseEntity<Void> aggiungiOggettoCarrelloCliente(UsernamePasswordAuthenticationToken token, @RequestBody AggiungiProdottoInCarrelloRequest request) {
        Utente u = (Utente)token.getPrincipal();
        boolean registratoOggettoCarrello = serviceOggettocarrello.aggiungiAlCarrello(u,request);
        if (registratoOggettoCarrello) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }


    @PostMapping("/cliente/sottraiQuantita")
    public ResponseEntity<Void> sottraiquanitàCliente(UsernamePasswordAuthenticationToken token,@RequestBody SottraiQuantitaRequest request) {
        Utente u = (Utente)token.getPrincipal();
        boolean sottraiQuantita = serviceOggettocarrello.sottraiQuantita(u,request);
        if (sottraiQuantita) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }


    @PostMapping("/cliente/eliminaoggettocarrello")
    public ResponseEntity<Void> eliminaoggettocarrelloCliente(UsernamePasswordAuthenticationToken token,@RequestBody EliminaOggettoCarrelloRequest request) {
        Utente u = (Utente)token.getPrincipal();
        boolean eliminato = serviceOggettocarrello.eliminaoggettocarrello(u,request);
        if (eliminato) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }

    @PostMapping("/clienteVenditore/aggiungiOggettoCarrello")
    public ResponseEntity<Void> aggiungiOggettoCarrelloClienteVenditore(UsernamePasswordAuthenticationToken token, @RequestBody AggiungiProdottoInCarrelloRequest request) {
        Utente u = (Utente)token.getPrincipal();
        boolean registratoOggettoCarrello = serviceOggettocarrello.aggiungiAlCarrello(u,request);
        if (registratoOggettoCarrello) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }


    @PostMapping("/clienteVenditore/sottraiQuantita")
    public ResponseEntity<Void> sottraiquanitàClienteVenditore(UsernamePasswordAuthenticationToken token,@RequestBody SottraiQuantitaRequest request) {
        Utente u = (Utente)token.getPrincipal();
        boolean sottraiQuantita = serviceOggettocarrello.sottraiQuantita(u,request);
        if (sottraiQuantita) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }


    @PostMapping("/clienteVenditore/eliminaoggettocarrello")
    public ResponseEntity<Void> eliminaoggettocarrelloClienteVenditore(UsernamePasswordAuthenticationToken token,@RequestBody EliminaOggettoCarrelloRequest request) {
        Utente u = (Utente)token.getPrincipal();
        boolean eliminato = serviceOggettocarrello.eliminaoggettocarrello(u,request);
        if (eliminato) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }


}
