package com.bytebazaar.bytebazaar.controller;

import com.bytebazaar.bytebazaar.dto.request.RegistrationOrModifyProdottoRequest;
import com.bytebazaar.bytebazaar.exception.messaggiException.NotFoundException;
import com.bytebazaar.bytebazaar.exception.messaggiException.UnAuthorizedException;
import com.bytebazaar.bytebazaar.model.Utente;
import com.bytebazaar.bytebazaar.service.definition.ProdottoService;
import lombok.SneakyThrows;
import org.apache.tomcat.util.buf.UEncoder;
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
    @PostMapping("/venditore/registraProdotto")
    public ResponseEntity<Void> registrazioneProdottoVenditore(UsernamePasswordAuthenticationToken token, @RequestBody RegistrationOrModifyProdottoRequest request) {
        Utente u = (Utente)token.getPrincipal();
        boolean registrato = serviceProdotto.registraProdotto(u,request);
        if (registrato) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }

    @PostMapping("/venditore/modificaProdotto")
    public ResponseEntity<Void> modificaProdottoVenditore(UsernamePasswordAuthenticationToken token,@RequestBody RegistrationOrModifyProdottoRequest request){
        Utente u = (Utente)token.getPrincipal();
        boolean registrato = serviceProdotto.modificaProdotto(u,request);
        if (registrato) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }

    //funzionalità del clienteVenditore
    @PostMapping("/clienteVenditore/registraProdotto")
    public ResponseEntity<Void> registrazioneProdottoClienteVenditore(UsernamePasswordAuthenticationToken token, @RequestBody RegistrationOrModifyProdottoRequest request) {
        Utente u = (Utente)token.getPrincipal();
        boolean registrato = serviceProdotto.registraProdotto(u,request);
        if (registrato) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }


    @PostMapping("/clienteVenditore/modificaProdotto")
    public ResponseEntity<Void> modificaProdottoClienteVenditore(UsernamePasswordAuthenticationToken token,@RequestBody RegistrationOrModifyProdottoRequest request){
        Utente u = (Utente)token.getPrincipal();
        boolean registrato = serviceProdotto.modificaProdotto(u,request);
        if (registrato) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }


}
