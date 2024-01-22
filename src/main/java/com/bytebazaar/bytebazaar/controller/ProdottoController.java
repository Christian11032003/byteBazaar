package com.bytebazaar.bytebazaar.controller;

import com.bytebazaar.bytebazaar.dto.request.InsertOrModifyProductRequest;
import com.bytebazaar.bytebazaar.model.Utente;
import com.bytebazaar.bytebazaar.service.definition.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProdottoController
{
    @Autowired
    ProdottoService serviceProdotto;


    //funzionalità del venditore
    @PostMapping({"/clienteVenditore/registraProdotto","/venditore/registraProdotto"})
    public ResponseEntity<Void> registrazioneProdotto(UsernamePasswordAuthenticationToken token, @RequestBody InsertOrModifyProductRequest request) {
        Utente u = (Utente)token.getPrincipal();
        boolean registrato = serviceProdotto.registraProdotto(u,request);
        if (registrato) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }

    @PostMapping({"/clienteVenditore/modificaProdotto","/venditore/modificaProdotto"})
    public ResponseEntity<Void> modificaProdotto(UsernamePasswordAuthenticationToken token,@RequestBody InsertOrModifyProductRequest request){
        Utente u = (Utente)token.getPrincipal();
        boolean registrato = serviceProdotto.modificaProdotto(u,request);
        if (registrato) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }



}
