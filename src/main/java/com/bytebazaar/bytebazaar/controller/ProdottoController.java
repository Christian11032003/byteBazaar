package com.bytebazaar.bytebazaar.controller;

import com.bytebazaar.bytebazaar.dto.request.InsertOrModifyProductRequest;
import com.bytebazaar.bytebazaar.facade.ProdottoFacade;
import com.bytebazaar.bytebazaar.model.Utente;
import com.bytebazaar.bytebazaar.service.definition.ProdottoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProdottoController
{

    private final ProdottoFacade prodottoFacade;




    //funzionalità del venditore
    @PostMapping("/venditore/registraProdotto")
    public ResponseEntity<Void> registrazioneProdotto(UsernamePasswordAuthenticationToken token, @RequestBody InsertOrModifyProductRequest request) {
        Utente u = (Utente)token.getPrincipal();
        boolean registrato = prodottoFacade.registraProdotto(u,request);
        if (registrato) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }

    @PostMapping("/venditore/modificaProdotto")
    public ResponseEntity<Void> modificaProdotto(UsernamePasswordAuthenticationToken token,@RequestBody InsertOrModifyProductRequest request){
        Utente u = (Utente)token.getPrincipal();
        boolean registrato = prodottoFacade.modificaProdotto(u,request);
        if (registrato) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }



}
