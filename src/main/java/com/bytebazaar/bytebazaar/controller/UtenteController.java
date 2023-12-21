package com.bytebazaar.bytebazaar.controller;

import com.bytebazaar.bytebazaar.dto.request.BannedOrUnBannedAdminRequest;
import com.bytebazaar.bytebazaar.dto.request.LoginRequest;
import com.bytebazaar.bytebazaar.dto.request.RegistrationUtenteRequest;
import com.bytebazaar.bytebazaar.dto.response.LoginResponse;
import com.bytebazaar.bytebazaar.exception.messaggiException.NotFoundException;
import com.bytebazaar.bytebazaar.exception.messaggiException.UnAuthorizedException;
import com.bytebazaar.bytebazaar.model.Prodotto;
import com.bytebazaar.bytebazaar.model.Utente;
import com.bytebazaar.bytebazaar.service.definition.UtenteService;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UtenteController
{

    @Autowired
    UtenteService serviceUtente;
    //funzionalità dell'admin
    @SneakyThrows
    @PostMapping("/bloccaSbloccaUtente")
    public ResponseEntity<Void> bloccaUtente(@RequestBody BannedOrUnBannedAdminRequest request){
        boolean bloccato = serviceUtente.bannedOrUnBannedAdminRequest(request);
        if (bloccato) return ResponseEntity.ok().build();
        else return  ResponseEntity.badRequest().build();
    }
    @SneakyThrows
    @PostMapping("/trovaTuttiClienti")
    public ResponseEntity<List<Utente>> findAllClienti(@RequestBody LoginRequest request) {
        List<Utente> clientiUsers = serviceUtente.findAllClienti(request);
        return ResponseEntity.status(HttpStatus.OK).body(clientiUsers);
    }
    @SneakyThrows
    @PostMapping("/trovaTuttiVenditori")
    public ResponseEntity<List<Utente>> findAllVenditori(@RequestBody LoginRequest request) {
        List<Utente> venditoriUsers = serviceUtente.findAllVenditori(request);
        return ResponseEntity.status(HttpStatus.OK).body(venditoriUsers);
    }

    @SneakyThrows
    @PostMapping("/trovaTuttiClientiVenditori")
    public ResponseEntity<List<Utente>> findAllClientiVenditori(@RequestBody LoginRequest request){
        List<Utente> clientiVenditoriUsers = serviceUtente.findAllClientiVenditori(request);
        return ResponseEntity.status(HttpStatus.OK).body(clientiVenditoriUsers);
    }

    //funzionalità del venditore
    @SneakyThrows
    @PostMapping("/trovaTuttiImieiProdotti")
    public ResponseEntity<List<Prodotto>> findAllHisProducts(@RequestBody LoginRequest request){
        List<Prodotto> prodottoListPersonal = serviceUtente.findAllHisProducts(request);
        return ResponseEntity.status(HttpStatus.OK).body(prodottoListPersonal);
    }

    //funzionalità del cliente
    @SneakyThrows
    @PostMapping("/trovaGliAltriProdotti")
    public ResponseEntity<List<Prodotto>> findAllOtherProducts(@RequestBody LoginRequest request){
        List<Prodotto> prodottoListOthers = serviceUtente.findAllOtherProducts(request);
        return ResponseEntity.status(HttpStatus.OK).body(prodottoListOthers);
    }


    //funzionalità di tutti
    @PostMapping("/registraUtente")
    public ResponseEntity<Void> registrazioneUtente(@Valid @RequestBody RegistrationUtenteRequest request){
        boolean registrato = serviceUtente.registrationUtente(request);
        if (registrato) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) throws NotFoundException {
        Utente u = serviceUtente.login(request);
        if(u != null)
        {
            LoginResponse lr = new LoginResponse();
            lr.setUsername(request.getUsername());
            lr.setRuolo(u.getRuolo().toString());

            return ResponseEntity.ok(lr);
        }

        else
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }




}
