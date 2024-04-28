package com.bytebazaar.bytebazaar.controller;

import com.bytebazaar.bytebazaar.dto.request.oggettoCarello.AddProductToCartRequestDTO;
import com.bytebazaar.bytebazaar.dto.request.oggettoCarello.DeleteObjectFromCartRequestDTO;
import com.bytebazaar.bytebazaar.dto.request.oggettoCarello.SubtractQuantityRequestDTO;
import com.bytebazaar.bytebazaar.facade.OggettoCarrelloFacade;
import com.bytebazaar.bytebazaar.model.Utente;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OggettoCarrelloController
{
    @Autowired
    private final OggettoCarrelloFacade oggettoCarrelloFacade;


    @PostMapping({"/venditore/aggiungiOggettoCarrello","/cliente/aggiungiOggettoCarrello"})
    public ResponseEntity<String> aggiungiOggettoCarrello(UsernamePasswordAuthenticationToken token, @RequestBody AddProductToCartRequestDTO request) {
        Utente u = (Utente)token.getPrincipal();
        boolean registratoOggettoCarrello = oggettoCarrelloFacade.aggiungiAlCarrello(u,request);
        if (registratoOggettoCarrello) return ResponseEntity.status(HttpStatus.OK).body("Prodotto aggiunto con successo nel carello");
        else return ResponseEntity.badRequest().build();
    }


    @PostMapping({"/venditore/sottraiQuantita","/cliente/sottraiQuantita"})
    public ResponseEntity<Void> sottraiquantita(@RequestBody SubtractQuantityRequestDTO request) {
        boolean sottraiQuantita = oggettoCarrelloFacade.sottraiQuantita(request);
        if (sottraiQuantita) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }


    @PostMapping({"/venditore/eliminaOggettoCarrello","/cliente/eliminaOggettoCarrello"})
    public ResponseEntity<Void> eliminaoggettocarrelloCliente(@RequestBody DeleteObjectFromCartRequestDTO request) {
        boolean eliminato = oggettoCarrelloFacade.eliminaoggettocarrello(request);
        if (eliminato) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }


}
