package com.bytebazaar.bytebazaar.controller;

import com.bytebazaar.bytebazaar.dto.request.RegistrationOrModifyProdottoRequest;
import com.bytebazaar.bytebazaar.exception.messaggiException.NotFoundException;
import com.bytebazaar.bytebazaar.exception.messaggiException.UnAuthorizedException;
import com.bytebazaar.bytebazaar.service.definition.ProdottoService;
import lombok.SneakyThrows;
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
    @SneakyThrows
    @PostMapping("/registraProdotto")
    public ResponseEntity<Void> registrazioneProdotto(@RequestBody RegistrationOrModifyProdottoRequest request) {
        boolean registrato = serviceProdotto.registraProdotto(request);
        if (registrato) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }
    @SneakyThrows
    @PostMapping("/modificaProdotto")
    public ResponseEntity<Void> modificaProdotto(@RequestBody RegistrationOrModifyProdottoRequest request){
        boolean registrato = serviceProdotto.modificaProdotto(request);
        if (registrato) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }
}
