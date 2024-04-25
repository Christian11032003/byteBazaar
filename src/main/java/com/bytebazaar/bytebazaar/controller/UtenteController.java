package com.bytebazaar.bytebazaar.controller;

import com.bytebazaar.bytebazaar.dto.request.utente.BannedOrUnBannedRequest;
import com.bytebazaar.bytebazaar.dto.request.utente.LoginRequest;
import com.bytebazaar.bytebazaar.dto.request.utente.RegistrationUserRequest;
import com.bytebazaar.bytebazaar.dto.response.LoginResponse;
import com.bytebazaar.bytebazaar.facade.UtenteFacade;
import com.bytebazaar.bytebazaar.model.Prodotto;
import com.bytebazaar.bytebazaar.model.Utente;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UtenteController
{

    private final UtenteFacade utenteFacade;


    //funzionalità del superAdmin
    @PostMapping("/superAdmin/bannedOrUnBannedAdmin")
    public ResponseEntity<Void> bloccaAdmin(@RequestBody BannedOrUnBannedRequest request){
        boolean bloccato = utenteFacade.bannedOrUnBannedAdmin(request);
        if (bloccato) return ResponseEntity.ok().build();
        else return  ResponseEntity.badRequest().build();
    }

    @PostMapping("/superAdmin/registrationAdmin")
    public ResponseEntity<Void> registrazioneAdmin(@Valid @RequestBody RegistrationUserRequest request){
        boolean registrato = utenteFacade.registrationAdmin(request);
        if (registrato) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }


    //funzionalità dell'admin
    @PostMapping("/admin/bannedOrUnBannedAdmin")
    public ResponseEntity<Void> bloccaUtente(@RequestBody BannedOrUnBannedRequest request){
        boolean bloccato = utenteFacade.bannedOrUnBannedUser(request);
        if (bloccato) return ResponseEntity.ok().build();
        else return  ResponseEntity.badRequest().build();
    }

    @GetMapping("/admin/trovaTuttiClienti")
    public ResponseEntity<List<Utente>> findAllClienti() {
        List<Utente> clientiUsers = utenteFacade.findAllClienti();
        return ResponseEntity.status(HttpStatus.OK).body(clientiUsers);
    }

    @GetMapping("/admin/trovaTuttiVenditori")
    public ResponseEntity<List<Utente>> findAllVenditori() {
        List<Utente> venditoriUsers = utenteFacade.findAllVenditori();
        return ResponseEntity.status(HttpStatus.OK).body(venditoriUsers);
    }

    //funzionalità del venditore

    @GetMapping("/venditore/trovaTuttiImieiProdotti")
    public ResponseEntity<List<Prodotto>> findAllHisProducts(@RequestBody LoginRequest request){
        List<Prodotto> prodottoListPersonal = utenteFacade.findAllHisProducts(request);
        return ResponseEntity.status(HttpStatus.OK).body(prodottoListPersonal);
    }

    //funzionalità del cliente e del venditore

    @GetMapping({"/venditore/trovaTuttiImieiProdotti","/cliente/trovaGliAltriProdotti"})
    public ResponseEntity<List<Prodotto>> findAllOtherProducts(@RequestBody LoginRequest request){
        List<Prodotto> prodottoListOthers = utenteFacade.findAllOtherProducts(request);
        return ResponseEntity.status(HttpStatus.OK).body(prodottoListOthers);
    }

    //funzionalità di tutti
    @PostMapping("/all/registration")
    public ResponseEntity<Void> registrazioneUtente(@Valid @RequestBody RegistrationUserRequest request){
        boolean registrato = utenteFacade.registrationUtente(request);
        if (registrato) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }


    @PostMapping("/all/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        Utente u = utenteFacade.login(request);
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
        boolean logout = utenteFacade.logout(u);
        if(logout) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();

    }


}
