package com.bytebazaar.bytebazaar.controller;

import com.bytebazaar.bytebazaar.facade.CarrelloFacade;
import com.bytebazaar.bytebazaar.model.Utente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//Il "CarrelloController" serve a confermare il carrello.

@RestController
@RequiredArgsConstructor
public class CarrelloController
{

    // Dipendenza da CarrelloFacade, utilizzata per gestire la logica di business relativa al carrello.
    private final CarrelloFacade carrelloFacade;

    /**
     * @param token il token di autenticazione contenente il token dell'utente.
     * @return un ResponseEntity con uno stato HTTP appropriato e il corpo del messaggio.
     */
    @GetMapping({"/cliente/confermaCarrello", "/venditore/confermaCarrello"})
    public ResponseEntity<String> confermaCarrello(UsernamePasswordAuthenticationToken token) {
        // Estrae l'utente (Utente) dal token di autenticazione.
        Utente u = (Utente) token.getPrincipal();

        // Tenta di confermare il carrello per l'utente.
        boolean bloccato = carrelloFacade.confermaCarrello(u);

        // Restituisce una risposta che indica il risultato del tentativo di conferma del carrello
        if (bloccato) {
            return ResponseEntity.status(HttpStatus.OK).body("Carrello confermato con successo");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
