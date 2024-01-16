package com.bytebazaar.bytebazaar.controller;

import com.bytebazaar.bytebazaar.dto.request.LoginRequest;
import com.bytebazaar.bytebazaar.dto.request.RegistrationUtenteRequest;
import com.bytebazaar.bytebazaar.dto.request.ChangeRequestAcceptRequest;
import com.bytebazaar.bytebazaar.exception.messaggiException.AlreadyReportedException;
import com.bytebazaar.bytebazaar.exception.messaggiException.NotFoundException;
import com.bytebazaar.bytebazaar.exception.messaggiException.UnAuthorizedException;
import com.bytebazaar.bytebazaar.model.Utente;
import com.bytebazaar.bytebazaar.security.TokenUtil;
import com.bytebazaar.bytebazaar.service.definition.RichiestaService;
import com.bytebazaar.bytebazaar.service.definition.UtenteService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RichiestaController
{
    @Autowired
    RichiestaService serviceRichiesta;

    @Autowired
    TokenUtil token;


    @PostMapping("/admin/cambioRichiestaStato")
    public ResponseEntity<Void> cambioRichiestaStato(UsernamePasswordAuthenticationToken token,@RequestBody ChangeRequestAcceptRequest request) {
        Utente u=(Utente) token.getPrincipal();
        boolean cambio = serviceRichiesta.changeRequestAcceptInRegistration(u,request);
        if (cambio) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }

    @GetMapping("/all/richiesta")
    public ResponseEntity<Void> richiesta(UsernamePasswordAuthenticationToken token)
    {
        Utente u=(Utente) token.getPrincipal();
        boolean cambio = serviceRichiesta.richiesta(u);
        if (cambio) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }
}
