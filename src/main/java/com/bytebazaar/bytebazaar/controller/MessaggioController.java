package com.bytebazaar.bytebazaar.controller;

import com.bytebazaar.bytebazaar.dto.request.SendMessageRequestDTO;
import com.bytebazaar.bytebazaar.dto.response.MessaggioResponseDTO;
import com.bytebazaar.bytebazaar.facade.MessaggioFacade;
import com.bytebazaar.bytebazaar.model.Utente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessaggioController
{

    private final MessaggioFacade messaggioFacade;

    @PostMapping({"/venditore/mandaMessaggio","/cliente/mandaMessaggio"})
    public ResponseEntity<MessaggioResponseDTO> mandaMessaggio(UsernamePasswordAuthenticationToken token, @RequestBody SendMessageRequestDTO request) {
        Utente u = (Utente)token.getPrincipal();
        boolean sendMessaggio = messaggioFacade.mandaMessaggio(u,request);
        if (sendMessaggio){

            MessaggioResponseDTO messaggioResponseDTO = new MessaggioResponseDTO.BuilderMessaggioResponseDTO()
                    .setIdDestinatario(request.getIdUtente())
                    .setTesto(request.getTestoMessaggio())
                    .build();


            return ResponseEntity.status(HttpStatus.OK).body(messaggioResponseDTO);
        }
        else return ResponseEntity.badRequest().build();
    }
}
