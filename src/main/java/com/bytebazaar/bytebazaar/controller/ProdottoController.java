package com.bytebazaar.bytebazaar.controller;

import com.bytebazaar.bytebazaar.dto.request.InsertOrModifyProductRequestDTO;
import com.bytebazaar.bytebazaar.dto.response.ProdottoReponseDTO;
import com.bytebazaar.bytebazaar.facade.ProdottoFacade;
import com.bytebazaar.bytebazaar.model.Utente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//l "ProdottoController" serve a registrare il prodotto o modificarlo
@RestController
@RequiredArgsConstructor
public class ProdottoController
{
    //Dipendenza da ProdottoFacade, utilizzata per gestire la logica di business relativa ai prodotti.
    private final ProdottoFacade prodottoFacade;

    /**
     * Metodo per la registrazione di un nuovo prodotto da parte di un venditore.
     *
     * @param token Token di autenticazione dell'utente
     * @param request Oggetto DTO che contiene i dettagli del prodotto da registrare
     * @return ResponseEntity contenente un oggetto DTO che rappresenta il prodotto registrato, se la registrazione ha avuto successo;
     * altrimenti, restituisce una risposta di errore con codice di stato HTTP 400 (Bad Request)
     */
    @PostMapping("/venditore/registraProdotto")
    public ResponseEntity<ProdottoReponseDTO> registrazioneProdotto(UsernamePasswordAuthenticationToken token, @RequestBody InsertOrModifyProductRequestDTO request) {
        // Ottiene l'utente autenticato dal token
        Utente u = (Utente) token.getPrincipal();
        // Chiama il metodo della facade per registrare il prodotto, passando l'utente e la richiesta
        boolean registrato = prodottoFacade.registraProdotto(u, request);
        if (registrato) {
            // Se la registrazione ha avuto successo, crea un oggetto DTO per la risposta con i dettagli del prodotto registrato
            ProdottoReponseDTO p = new ProdottoReponseDTO.BuilderProdottoDTO()
                    .setNome(request.getNome())
                    .setDescrizione(request.getDescrizione())
                    .setQuantita(request.getQuantita())
                    .setPrezzo(request.getPrezzo())
                    .setCondizione(request.getCondizione())
                    .build();

            // Restituisce una risposta con codice di stato HTTP 200 (OK) contenente l'oggetto DTO del prodotto registrato
            return ResponseEntity.status(HttpStatus.OK).body(p);
        } else {
            // Se la registrazione non ha avuto successo, restituisce una risposta di errore con codice di stato HTTP 400 (Bad Request)
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Metodo per la modifica di un prodotto esistente da parte di un venditore.
     *
     * @param token Token di autenticazione dell'utente
     * @param request Oggetto DTO che contiene i dettagli del prodotto da modificare
     * @return ResponseEntity contenente un oggetto DTO che rappresenta il prodotto modificato, se la modifica ha avuto successo;
     * altrimenti, restituisce una risposta di errore con codice di stato HTTP 400 (Bad Request)
     */
    @PostMapping("/venditore/modificaProdotto")
    public ResponseEntity<ProdottoReponseDTO> modificaProdotto(UsernamePasswordAuthenticationToken token, @RequestBody InsertOrModifyProductRequestDTO request) {
        // Ottiene l'utente autenticato dal token
        Utente u = (Utente) token.getPrincipal();
        // Chiama il metodo della facade per modificare il prodotto, passando l'utente e la richiesta
        boolean registrato = prodottoFacade.modificaProdotto(u, request);
        if (registrato) {
            // Se la modifica ha avuto successo, crea un oggetto DTO per la risposta con i dettagli del prodotto modificato
            ProdottoReponseDTO p = new ProdottoReponseDTO.BuilderProdottoDTO()
                    .setNome(request.getNome())
                    .setDescrizione(request.getDescrizione())
                    .setQuantita(request.getQuantita())
                    .setPrezzo(request.getPrezzo())
                    .setCondizione(request.getCondizione())
                    .build();

            // Restituisce una risposta con codice di stato HTTP 200 (OK) contenente l'oggetto DTO del prodotto modificato
            return ResponseEntity.status(HttpStatus.OK).body(p);
        } else {
            // Se la modifica non ha avuto successo, restituisce una risposta di errore con codice di stato HTTP 400 (Bad Request)
            return ResponseEntity.badRequest().build();
        }
    }




}
