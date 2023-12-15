package com.bytebazaar.bytebazaar.controller;

import com.bytebazaar.bytebazaar.dto.request.LoginRequest;
import com.bytebazaar.bytebazaar.dto.request.MandaMessaggioRequest;
import com.bytebazaar.bytebazaar.service.definition.CarrelloService;
import com.bytebazaar.bytebazaar.service.definition.MessaggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessaggioController
{
    @Autowired
    MessaggioService serviceMessaggio;

    @PostMapping("/mandaMessaggio")
    public ResponseEntity<Void> mandaMessaggio(@RequestBody MandaMessaggioRequest request) {
        boolean sendMessaggio = serviceMessaggio.mandaMessaggio(request);
        if (sendMessaggio) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }
}
