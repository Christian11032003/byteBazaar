package com.bytebazaar.bytebazaar.controller;

import com.bytebazaar.bytebazaar.dto.request.BannedOrUnBannedAdminRequest;
import com.bytebazaar.bytebazaar.dto.request.LoginRequest;
import com.bytebazaar.bytebazaar.dto.request.RegistrationUserRequest;
import com.bytebazaar.bytebazaar.dto.response.LoginResponse;
import com.bytebazaar.bytebazaar.model.Prodotto;
import com.bytebazaar.bytebazaar.model.Utente;
import com.bytebazaar.bytebazaar.security.TokenUtil;
import com.bytebazaar.bytebazaar.service.definition.UtenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UtenteController
{

    @Autowired
    UtenteService serviceUtente;

    @Autowired
    TokenUtil util;
    //funzionalità dell'admin


    @PostMapping("/admin/bloccaSbloccaUtente")
    public ResponseEntity<Void> bloccaUtente(@RequestBody BannedOrUnBannedAdminRequest request){
        boolean bloccato = serviceUtente.bannedOrUnBannedAdminRequest(request);
        if (bloccato) return ResponseEntity.ok().build();
        else return  ResponseEntity.badRequest().build();
    }

    @GetMapping("/admin/trovaTuttiClienti")
    public ResponseEntity<List<Utente>> findAllClienti() {
        List<Utente> clientiUsers = serviceUtente.findAllClienti();
        return ResponseEntity.status(HttpStatus.OK).body(clientiUsers);
    }

    @GetMapping("/admin/trovaTuttiVenditori")
    public ResponseEntity<List<Utente>> findAllVenditori() {
        List<Utente> venditoriUsers = serviceUtente.findAllVenditori();
        return ResponseEntity.status(HttpStatus.OK).body(venditoriUsers);
    }


    @GetMapping("/admin/trovaTuttiClientiVenditori")
    public ResponseEntity<List<Utente>> findAllClientiVenditori(){
        List<Utente> clientiVenditoriUsers = serviceUtente.findAllClientiVenditori();
        return ResponseEntity.status(HttpStatus.OK).body(clientiVenditoriUsers);
    }

    //funzionalità del venditore

    @GetMapping("/venditore/trovaTuttiImieiProdotti")
    public ResponseEntity<List<Prodotto>> findAllHisProducts(@RequestBody LoginRequest request){
        List<Prodotto> prodottoListPersonal = serviceUtente.findAllHisProducts(request);
        return ResponseEntity.status(HttpStatus.OK).body(prodottoListPersonal);
    }

    //funzionalità del cliente

    @PostMapping("/cliente/trovaGliAltriProdotti")
    public ResponseEntity<List<Prodotto>> findAllOtherProducts(@RequestBody LoginRequest request){
        List<Prodotto> prodottoListOthers = serviceUtente.findAllOtherProducts(request);
        return ResponseEntity.status(HttpStatus.OK).body(prodottoListOthers);
    }

    //funzionalità di tutti
    @PostMapping("/all/registration")
    public ResponseEntity<Void> registrazioneUtente(@Valid @RequestBody RegistrationUserRequest request){
        boolean registrato = serviceUtente.registrationUtente(request);
        if (registrato) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }


    @PostMapping("/all/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        Utente u = serviceUtente.login(request);
        if(u != null)
        {
            LoginResponse lr = new LoginResponse();
            lr.setUsername(request.getUsername());
            lr.setRuolo(u.getRuolo().toString());

            String token= u.getToken();
            return ResponseEntity.status(HttpStatus.OK).header("Authorization",token).body(lr);
        }

        else
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/all/logout")
    public ResponseEntity<Void> logout(UsernamePasswordAuthenticationToken token)
    {
        Utente u=(Utente) token.getPrincipal();
        boolean logout = serviceUtente.logout(u);
        if(logout) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();

    }


}
