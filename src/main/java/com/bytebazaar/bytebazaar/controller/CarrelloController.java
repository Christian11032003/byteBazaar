package com.bytebazaar.bytebazaar.controller;

import com.bytebazaar.bytebazaar.dto.request.BannedOrUnBannedAdminRequest;
import com.bytebazaar.bytebazaar.dto.request.LoginRequest;
import com.bytebazaar.bytebazaar.service.definition.CarrelloService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarrelloController
{
    @Autowired
    CarrelloService serviceCarrello;
    @SneakyThrows
    @PostMapping("/confermaCarrello")
    public ResponseEntity<Void> confermaCarrello(@RequestBody LoginRequest request) {
        boolean bloccato = serviceCarrello.confermaCarrello(request);
        if (bloccato) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }
}
