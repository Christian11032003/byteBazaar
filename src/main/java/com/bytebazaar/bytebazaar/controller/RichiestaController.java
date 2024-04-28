package com.bytebazaar.bytebazaar.controller;

import com.bytebazaar.bytebazaar.dto.request.richiesta.AcceptOrRejectRequestDTO;
import com.bytebazaar.bytebazaar.facade.RichiestaFacade;

import com.bytebazaar.bytebazaar.model.Utente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RichiestaController
{

    private final RichiestaFacade richiestaFacade;

    @PostMapping("/admin/modifyTheRequest")
    public ResponseEntity<String> modifyTheRequest(@RequestBody AcceptOrRejectRequestDTO request)
    {

        boolean cambio = richiestaFacade.modifyTheRequest(request);
        if (cambio) return ResponseEntity.status(HttpStatus.OK).body("Richiesta cambiata con successo");
        else return ResponseEntity.badRequest().build();
    }

    @GetMapping("/cliente/richiesta")
    public ResponseEntity<String> richiesta(UsernamePasswordAuthenticationToken token)
    {
        Utente u=(Utente) token.getPrincipal();
        System.out.println("oooooooooo");
        boolean cambio = richiestaFacade.richiesta(u);
        if (cambio) return ResponseEntity.status(HttpStatus.OK).body("Richiesta inoltrata con successo");
        else return ResponseEntity.badRequest().build();
    }
}
