package com.bytebazaar.bytebazaar.controller;

import com.bytebazaar.bytebazaar.dto.request.AddProductToCartRequestDTO;
import com.bytebazaar.bytebazaar.dto.request.DeleteObjectFromCartRequestDTO;
import com.bytebazaar.bytebazaar.dto.request.SubtractQuantityRequestDTO;
import com.bytebazaar.bytebazaar.dto.response.OggettoCarrelloReponseDTO;
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
    public ResponseEntity<OggettoCarrelloReponseDTO> aggiungiOggettoCarrello(UsernamePasswordAuthenticationToken token, @RequestBody AddProductToCartRequestDTO request) {
        Utente u = (Utente)token.getPrincipal();
        boolean registratoOggettoCarrello = oggettoCarrelloFacade.aggiungiAlCarrello(u,request);
        if (registratoOggettoCarrello)
        {
            OggettoCarrelloReponseDTO oggettoCarrelloReponseDTO = new OggettoCarrelloReponseDTO.BuilderOggettoCarrelloDTO()
                    .setId(request.getIdProdotto())
                    .setQuantita(request.getQuantita())
                    .build();

            return ResponseEntity.status(HttpStatus.OK).body(oggettoCarrelloReponseDTO);
        }
        else return ResponseEntity.badRequest().build();
    }


    @PostMapping({"/venditore/sottraiQuantita","/cliente/sottraiQuantita"})
    public ResponseEntity<String> sottraiquantita(@RequestBody SubtractQuantityRequestDTO request) {
        boolean sottraiQuantita = oggettoCarrelloFacade.sottraiQuantita(request);
        if (sottraiQuantita) return ResponseEntity.status(HttpStatus.OK).body("Quantità modificata con successo");
        else return ResponseEntity.badRequest().build();
    }


    @PostMapping({"/venditore/eliminaOggettoCarrello","/cliente/eliminaOggettoCarrello"})
    public ResponseEntity<String> eliminaoggettocarrello(@RequestBody DeleteObjectFromCartRequestDTO request) {
        boolean eliminato = oggettoCarrelloFacade.eliminaoggettocarrello(request);
        if (eliminato) return ResponseEntity.status(HttpStatus.OK).body("Prodotto eliminato dal carrello ");
        else return ResponseEntity.badRequest().build();
    }


}
