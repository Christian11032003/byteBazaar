package com.bytebazaar.bytebazaar.controller;

import com.bytebazaar.bytebazaar.dto.request.AcceptOrRejectRequestDTO;
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


//la "RichiestaController" serve per abilitare un utente a vendere
@RestController
@RequiredArgsConstructor
public class RichiestaController
{

    // Iniezione di dipendenza per RichiestaFacade, che gestisce la logica di business
    private final RichiestaFacade richiestaFacade;


    /**
     * @param request i dati della richiesta incapsulati in AcceptOrRejectRequestDTO
     * @return un ResponseEntity con un messaggio di stato
     */
    @PostMapping("/admin/modifyTheRequest")
    public ResponseEntity<String> modifyTheRequest(@RequestBody AcceptOrRejectRequestDTO request) {
        // Chiama il metodo modifyTheRequest di richiestaFacade e cattura il risultato
        boolean cambio = richiestaFacade.modifyTheRequest(request);

        // Se la modifica della richiesta è stata avviata con successo, restituisce una risposta OK
        if (cambio) {
            return ResponseEntity.status(HttpStatus.OK).body("Richiesta cambiata con successo");
        } else {
            // Se la modifica della richiesta è fallita, restituisce una risposta bad request
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * @param token il token di autenticazione dell'utente
     * @return un ResponseEntity con un messaggio di stato
     */
    @GetMapping("/cliente/richiesta")
    public ResponseEntity<String> richiesta(UsernamePasswordAuthenticationToken token) {
        // Estrae l'utente (Utente) dal token di autenticazione
        Utente u = (Utente) token.getPrincipal();

        // Chiama il metodo richiesta di richiestaFacade e cattura il risultato
        boolean cambio = richiestaFacade.richiesta(u);

        // Se la richiesta è stata inoltrata con successo, restituisce una risposta OK
        if (cambio) {
            return ResponseEntity.status(HttpStatus.OK).body("Richiesta inoltrata con successo");
        } else {
            // Se l'inoltro della richiesta è fallito, restituisce una risposta bad request
            return ResponseEntity.badRequest().build();
        }
    }
}
