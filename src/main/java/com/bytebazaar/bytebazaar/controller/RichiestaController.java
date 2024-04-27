package com.bytebazaar.bytebazaar.controller;

import com.bytebazaar.bytebazaar.dto.request.richiesta.AcceptOrRejectRequestDTO;
import com.bytebazaar.bytebazaar.facade.RichiestaFacade;

import com.bytebazaar.bytebazaar.model.Utente;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<Void> modifyTheRequest(@RequestBody AcceptOrRejectRequestDTO request)
    {

        boolean cambio = richiestaFacade.modifyTheRequest(request);
        if (cambio) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }

    @GetMapping("/cliente/richiesta")
    public ResponseEntity<Void> richiesta(UsernamePasswordAuthenticationToken token)
    {
        Utente u=(Utente) token.getPrincipal();
        boolean cambio = richiestaFacade.richiesta(u);
        if (cambio) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }
}
