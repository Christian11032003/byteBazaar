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

// La classe MessaggioController gestisce le operazioni relative all'invio e alla gestione dei messaggi
@RestController
@RequiredArgsConstructor
public class MessaggioController
{

    // Dipendenza da MessaggioFacade, utilizzata per gestire la logica di business relativa ai messaggi.
    private final MessaggioFacade messaggioFacade;


    /**
     * @param token il token di autenticazione contenente il token dell'utente.
     * @param request il corpo della richiesta contenente i dettagli del messaggio.
     * @return un ResponseEntity con uno stato HTTP appropriato e il corpo del messaggio.
     */
    @PostMapping({"/venditore/mandaMessaggio", "/cliente/mandaMessaggio"})
    public ResponseEntity<MessaggioResponseDTO> mandaMessaggio(UsernamePasswordAuthenticationToken token, @RequestBody SendMessageRequestDTO request) {
        // Estrae l'utente (Utente) dal token di autenticazione.
        Utente u = (Utente) token.getPrincipal();

        // Tenta di inviare il messaggio per l'utente.
        boolean sendMessaggio = messaggioFacade.mandaMessaggio(u, request);

        // Se il messaggio è stato inviato con successo, costruisce la risposta.
        if (sendMessaggio) {
            MessaggioResponseDTO messaggioResponseDTO = new MessaggioResponseDTO.BuilderMessaggioResponseDTO()
                    .setIdDestinatario(request.getIdUtente())
                    .setTesto(request.getTestoMessaggio())
                    .build();

            return ResponseEntity.status(HttpStatus.OK).body(messaggioResponseDTO);
        } else {
            // Se il messaggio non è stato inviato, restituisce un Bad Request.
            return ResponseEntity.badRequest().build();
        }
    }
}
