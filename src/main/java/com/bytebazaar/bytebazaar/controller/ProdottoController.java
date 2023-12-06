package com.bytebazaar.bytebazaar.controller;

import com.bytebazaar.bytebazaar.dto.request.ModificaProdottoRequest;
import com.bytebazaar.bytebazaar.dto.request.RegistrationProdottoRequest;
import com.bytebazaar.bytebazaar.dto.request.RegistrationUtenteRequest;
import com.bytebazaar.bytebazaar.service.definition.ProdottoService;
import com.bytebazaar.bytebazaar.service.definition.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProdottoController
{
    @Autowired
    ProdottoService serviceProdotto;

    @PostMapping("/registraProdotto")
    public ResponseEntity<Void> registrazioneProdotto(@RequestBody RegistrationProdottoRequest request) {
        boolean registrato = serviceProdotto.registraProdotto(request);
        if (registrato) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }

    @PostMapping("/modificaProdotto")
    public ResponseEntity<Void> modificaProdotto(@RequestBody ModificaProdottoRequest request) {
        boolean registrato = serviceProdotto.modificaProdotto(request);
        if (registrato) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }
}
