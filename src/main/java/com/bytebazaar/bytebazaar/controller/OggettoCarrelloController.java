package com.bytebazaar.bytebazaar.controller;

import com.bytebazaar.bytebazaar.dto.request.AggiungiProdottoInCarrelloRequest;
import com.bytebazaar.bytebazaar.exception.messaggiException.NotFoundException;
import com.bytebazaar.bytebazaar.exception.messaggiException.UnAuthorizedException;
import com.bytebazaar.bytebazaar.service.definition.OggettocarrelloService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OggettoCarrelloController
{
    @Autowired
    OggettocarrelloService serviceOggettocarrello;

    @SneakyThrows
    @PostMapping("/aggiungiOggettoCarrello")
    public ResponseEntity<Void> aggiungiOggettoCarrello(@RequestBody AggiungiProdottoInCarrelloRequest request) {
        boolean registrato = serviceOggettocarrello.aggiungiAlCarrello(request);
        if (registrato) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }
}
