package com.bytebazaar.bytebazaar.controller;

import com.bytebazaar.bytebazaar.dto.request.*;
import com.bytebazaar.bytebazaar.dto.response.LoginResponseDTO;
import com.bytebazaar.bytebazaar.dto.response.RegistrationUserResponseDTO;
import com.bytebazaar.bytebazaar.facade.UtenteFacade;
import com.bytebazaar.bytebazaar.model.Feedback;
import com.bytebazaar.bytebazaar.model.Messaggio;
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
    public ResponseEntity<Void> bloccaAdmin(@RequestBody BannedOrUnBannedRequestDTO request){
        boolean bloccato = utenteFacade.bannedOrUnBannedAdmin(request);
        if (bloccato) return ResponseEntity.ok().build();
        else return  ResponseEntity.badRequest().build();
    }

    @GetMapping("/superAdmin/findAllAdmin")
    public ResponseEntity<List<Utente>> findAllAdmin() {
        List<Utente> clientiUsers = utenteFacade.findAllAdmin();
        return ResponseEntity.status(HttpStatus.OK).body(clientiUsers);
    }


    @PostMapping("/superAdmin/registrationAdmin")
    public ResponseEntity<LoginResponseDTO> registrazioneAdmin(@Valid @RequestBody RegistrationUserRequestDTO request){
        boolean registrato = utenteFacade.registrationAdmin(request);
        if (registrato) {

            LoginResponseDTO lr = new LoginResponseDTO.BuilderLoginResponseDTO()
                    .setUsername(request.getUsername())
                    .setRuolo(request.getRuolo().toString())
                    .build();

            return ResponseEntity.status(HttpStatus.OK).body(lr);
        }


        else return ResponseEntity.badRequest().build();
    }


    //funzionalità dell'admin
    @PostMapping("/admin/bannedOrUnBannedAdmin")
    public ResponseEntity<Void> bloccaUtente(@RequestBody BannedOrUnBannedRequestDTO request){
        boolean bloccato = utenteFacade.bannedOrUnBannedUser(request);
        if (bloccato) return ResponseEntity.ok().build();
        else return  ResponseEntity.badRequest().build();
    }

    @GetMapping("/admin/findAllClienti")
    public ResponseEntity<List<Utente>> findAllClienti() {
        List<Utente> clientiUsers = utenteFacade.findAllClienti();
        return ResponseEntity.status(HttpStatus.OK).body(clientiUsers);
    }

    @GetMapping("/admin/findAllVenditori")
    public ResponseEntity<List<Utente>> findAllVenditori() {
        List<Utente> venditoriUsers = utenteFacade.findAllVenditori();
        return ResponseEntity.status(HttpStatus.OK).body(venditoriUsers);
    }

    @PostMapping("/admin/findAllUtenteProduct")
    public ResponseEntity<List<Prodotto>> findAllUtenteProduct(@RequestBody FindThingsRequestDTO request) {
        List<Prodotto> prodotti = utenteFacade.findAllProdottiUser(request);
        return ResponseEntity.status(HttpStatus.OK).body(prodotti);
    }

    @PostMapping("/admin/findAllMessagesUser")
    public ResponseEntity<List<Messaggio>> findAllMessaggeUtente(@RequestBody FindThingsRequestDTO request) {
        List<Messaggio> messaggi = utenteFacade.findAllMessaggeUser(request);
        return ResponseEntity.status(HttpStatus.OK).body(messaggi);
    }

    @PostMapping("/admin/findAllFeedbackUser")
    public ResponseEntity<List<Feedback>> findAllFeedbackUtente(@RequestBody FindThingsRequestDTO request) {
        List<Feedback> feedbacks = utenteFacade.findAllFeedbackUser(request);
        return ResponseEntity.status(HttpStatus.OK).body(feedbacks);
    }

    @PostMapping("/admin/findAllProdottiUserInKart")
    public ResponseEntity<List<Prodotto>> findAllProdottiUserInKart(@RequestBody FindThingsRequestDTO request) {
        List<Prodotto> prodotti = utenteFacade.findAllProdottiUserInKart(request);
        return ResponseEntity.status(HttpStatus.OK).body(prodotti);
    }

    

    //funzionalità del venditore

    @GetMapping("/venditore/findMyOwnProduct")
    public ResponseEntity<List<Prodotto>> findAllHisProducts(UsernamePasswordAuthenticationToken token)
    {
        Utente u=(Utente) token.getPrincipal();
        List<Prodotto> prodottoListPersonal = utenteFacade.findAllHisProducts(u);
        return ResponseEntity.status(HttpStatus.OK).body(prodottoListPersonal);
    }

    //funzionalità del cliente e del venditore
    @PostMapping({"/venditore/findTheOtherProductToCondition","/cliente/findTheOtherProductToCondition"})
    public ResponseEntity<List<Prodotto>> findAllOtherProductsToCondition(UsernamePasswordAuthenticationToken token, @RequestBody FilterProductRequestDTO request)
    {
        Utente u=(Utente) token.getPrincipal();
        List<Prodotto> prodottoListOthers = utenteFacade.findAllOtherProducts(u, request);
        return ResponseEntity.status(HttpStatus.OK).body(prodottoListOthers);
    }


    @GetMapping({"/venditore/findMyOwnMessage","/cliente/findMyOwnMessage"})
    public ResponseEntity<List<Messaggio>> findMyOwnMessage(UsernamePasswordAuthenticationToken token)
    {
        Utente u=(Utente) token.getPrincipal();
        List<Messaggio> myOwnMessages = utenteFacade.findMyOwnMessage(u);
        return ResponseEntity.status(HttpStatus.OK).body(myOwnMessages);
    }


    @GetMapping({"/venditore/findMyOwnFeedback","/cliente/findMyOwnFeedback"})
    public ResponseEntity<List<Feedback>> findMyOwnFeedback(UsernamePasswordAuthenticationToken token)
    {
        Utente u=(Utente) token.getPrincipal();
        List<Feedback> myOwnMessages = utenteFacade.findMyOwnFeedback(u);
        return ResponseEntity.status(HttpStatus.OK).body(myOwnMessages);
    }

    //funzionalità di tutti
    @PostMapping("/all/registration")
    public ResponseEntity<RegistrationUserResponseDTO> registrazioneUtente(@Valid @RequestBody RegistrationUserRequestDTO request){
        boolean registrato = utenteFacade.registrationUtente(request);
        if (registrato)
        {
            RegistrationUserResponseDTO registrationUserResponseDTO = new RegistrationUserResponseDTO.BuilderRegistrationUserDTO()
                    .setNome(request.getNome())
                    .setCognome(request.getCognome())
                    .setEmail(request.getEmail())
                    .setUsername(request.getUsername())
                    .setPassword(request.getPassword())
                    .setRuolo(request.getRuolo())
                    .build();

            return ResponseEntity.status(HttpStatus.OK).body(registrationUserResponseDTO);
        }
        else return ResponseEntity.badRequest().build();
    }


    @PostMapping("/all/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request){
        Utente u = utenteFacade.login(request);
        if(u != null)
        {
            LoginResponseDTO lr = new LoginResponseDTO.BuilderLoginResponseDTO()
                    .setUsername(request.getUsername())
                    .setRuolo(u.getRuolo().toString())
                    .build();

            String token= u.getToken();
            return ResponseEntity.status(HttpStatus.OK).header("Authorization",token).body(lr);
        }

        else
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/all/logout")
    public ResponseEntity<String> logout(UsernamePasswordAuthenticationToken token)
    {
        Utente u=(Utente) token.getPrincipal();
        boolean logout = utenteFacade.logout(u);
        if(logout) return ResponseEntity.status(HttpStatus.OK).body("Disconesso con successo");
        else return ResponseEntity.badRequest().build();

    }


}
