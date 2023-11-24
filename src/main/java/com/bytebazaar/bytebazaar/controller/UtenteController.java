package com.bytebazaar.bytebazaar.controller;

import com.bytebazaar.bytebazaar.dto.request.RegistrationUtenteRequest;
import com.bytebazaar.bytebazaar.service.definition.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UtenteController
{

    @Autowired
    UtenteService serviceUtente;

    @PostMapping("/registraUtente")
    public ResponseEntity<Void> registrazioneCliente(@RequestBody RegistrationUtenteRequest request) {
        boolean registrato = serviceUtente.registrazioneUtente(request);
        if (registrato) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }
}
