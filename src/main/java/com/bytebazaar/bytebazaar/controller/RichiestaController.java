package com.bytebazaar.bytebazaar.controller;

import com.bytebazaar.bytebazaar.dto.request.RegistrationUtenteRequest;
import com.bytebazaar.bytebazaar.dto.request.changeRequestAcceptRequest;
import com.bytebazaar.bytebazaar.service.definition.RichiestaService;
import com.bytebazaar.bytebazaar.service.definition.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RichiestaController
{
    @Autowired
    RichiestaService serviceRichiesta;

    @PostMapping("/cambioRichiestaStato")
    public ResponseEntity<Void> cambioRichiestaStato(@RequestBody changeRequestAcceptRequest request) {
        boolean cambio = serviceRichiesta.changeRequestAccept(request);
        if (cambio) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }
}
