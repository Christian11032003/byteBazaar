package com.bytebazaar.bytebazaar.controller;

import com.bytebazaar.bytebazaar.dto.request.*;
import com.bytebazaar.bytebazaar.dto.response.*;
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
public class UtenteController {

    // Iniezione di dipendenza per UtenteFacade, che gestisce la logica di business
    private final UtenteFacade utenteFacade;

    // Funzionalità Admin

    /**
     * Endpoint per bannare o sbannare un admin.
     * @param request i dati della richiesta incapsulati in BannedOrUnBannedRequestDTO
     * @return un ResponseEntity con lo stato della richiesta
     */
    @PostMapping("/superAdmin/bannedOrUnBannedAdmin")
    public ResponseEntity<Void> bloccaAdmin(@RequestBody BannedOrUnBannedRequestDTO request) {
        boolean bloccato = utenteFacade.bannedOrUnBannedAdmin(request);
        if (bloccato) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }

    /**
     * Endpoint per trovare tutti gli admin.
     * @return un ResponseEntity con la lista di tutti gli admin
     */
    @GetMapping("/superAdmin/findAllAdmin")
    public ResponseEntity<List<Utente>> findAllAdmin() {
        List<Utente> clientiUsers = utenteFacade.findAllAdmin();
        return ResponseEntity.status(HttpStatus.OK).body(clientiUsers);
    }

    /**
     * Endpoint per la registrazione di un admin.
     * @param request i dati della richiesta di registrazione incapsulati in RegistrationUserRequestDTO
     * @return un ResponseEntity con i dettagli del login se la registrazione ha successo
     */
    @PostMapping("/superAdmin/registrationAdmin")
    public ResponseEntity<LoginResponseDTO> registrazioneAdmin(@Valid @RequestBody RegistrationUserRequestDTO request) {
        boolean registrato = utenteFacade.registrationAdmin(request);
        if (registrato) {
            LoginResponseDTO lr = new LoginResponseDTO.BuilderLoginResponseDTO()
                    .setUsername(request.getUsername())
                    .setRuolo(request.getRuolo().toString())
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(lr);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    // Funzionalità dell'admin

    /**
     * Endpoint per bannare o sbannare un utente.
     * @param request i dati della richiesta incapsulati in BannedOrUnBannedRequestDTO
     * @return un ResponseEntity con lo stato della richiesta
     */
    @PostMapping("/admin/bannedOrUnBannedAdmin")
    public ResponseEntity<Void> bloccaUtente(@RequestBody BannedOrUnBannedRequestDTO request) {
        boolean bloccato = utenteFacade.bannedOrUnBannedUser(request);
        if (bloccato) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }

    /**
     * Endpoint per trovare tutti i clienti.
     * @return un ResponseEntity con la lista di tutti i clienti
     */
    @GetMapping("/admin/findAllClienti")
    public ResponseEntity<List<Utente>> findAllClienti() {
        List<Utente> clientiUsers = utenteFacade.findAllClienti();
        return ResponseEntity.status(HttpStatus.OK).body(clientiUsers);
    }

    /**
     * Endpoint per trovare tutti i venditori.
     * @return un ResponseEntity con la lista di tutti i venditori
     */
    @GetMapping("/admin/findAllVenditori")
    public ResponseEntity<List<Utente>> findAllVenditori() {
        List<Utente> venditoriUsers = utenteFacade.findAllVenditori();
        return ResponseEntity.status(HttpStatus.OK).body(venditoriUsers);
    }

    /**
     * Endpoint per trovare tutti i prodotti di un utente specifico.
     * @param request i dati della richiesta incapsulati in FindThingsRequestDTO
     * @return un ResponseEntity con la lista di tutti i prodotti dell'utente
     */
    @PostMapping("/admin/findAllUtenteProduct")
    public ResponseEntity<List<Prodotto>> findAllUtenteProduct(@RequestBody FindThingsRequestDTO request) {
        List<Prodotto> prodotti = utenteFacade.findAllProdottiUser(request);
        return ResponseEntity.status(HttpStatus.OK).body(prodotti);
    }

    /**
     * Endpoint per trovare tutti i messaggi di un utente specifico.
     * @param request i dati della richiesta incapsulati in FindThingsRequestDTO
     * @return un ResponseEntity con la lista di tutti i messaggi dell'utente
     */
    @PostMapping("/admin/findAllMessagesUser")
    public ResponseEntity<List<Messaggio>> findAllMessaggeUtente(@RequestBody FindThingsRequestDTO request) {
        List<Messaggio> messaggi = utenteFacade.findAllMessaggeUser(request);
        return ResponseEntity.status(HttpStatus.OK).body(messaggi);
    }

    /**
     * Endpoint per trovare tutti i feedback di un utente specifico.
     * @param request i dati della richiesta incapsulati in FindThingsRequestDTO
     * @return un ResponseEntity con la lista di tutti i feedback dell'utente
     */
    @PostMapping("/admin/findAllFeedbackUser")
    public ResponseEntity<List<Feedback>> findAllFeedbackUtente(@RequestBody FindThingsRequestDTO request) {
        List<Feedback> feedbacks = utenteFacade.findAllFeedbackUser(request);
        return ResponseEntity.status(HttpStatus.OK).body(feedbacks);
    }

    /**
     * Endpoint per trovare tutti i prodotti di un utente nel carrello.
     * @param request i dati della richiesta incapsulati in FindThingsRequestDTO
     * @return un ResponseEntity con la lista di tutti i prodotti nel carrello dell'utente
     */
    @PostMapping("/admin/findAllProdottiUserInKart")
    public ResponseEntity<List<Prodotto>> findAllProdottiUserInKart(@RequestBody FindThingsRequestDTO request) {
        List<Prodotto> prodotti = utenteFacade.findAllProdottiUserInKart(request);
        return ResponseEntity.status(HttpStatus.OK).body(prodotti);
    }

    // Funzionalità del venditore

    /**
     * Endpoint per trovare tutti i prodotti di proprietà di un venditore.
     * @param token il token di autenticazione dell'utente
     * @return un ResponseEntity con la lista di tutti i prodotti del venditore
     */
    @GetMapping("/venditore/findMyOwnProduct")
    public ResponseEntity<List<ProdottoReponseDTO>> findAllHisProducts(UsernamePasswordAuthenticationToken token) {
        Utente u = (Utente) token.getPrincipal();
        List<ProdottoReponseDTO> prodottoListPersonal = utenteFacade.findAllHisProducts(u);
        return ResponseEntity.status(HttpStatus.OK).body(prodottoListPersonal);
    }

    // Funzionalità del cliente e del venditore

    /**
     * Endpoint per trovare tutti gli altri prodotti in base a una condizione.
     * @param token il token di autenticazione dell'utente
     * @param request i dati della richiesta incapsulati in FilterProductRequestDTO
     * @return un ResponseEntity con la lista di tutti gli altri prodotti che soddisfano la condizione
     */
    @PostMapping({"/venditore/findTheOtherProductToCondition", "/cliente/findTheOtherProductToCondition"})
    public ResponseEntity<List<ProdottoReponseDTO>> findAllOtherProductsToCondition(UsernamePasswordAuthenticationToken token, @RequestBody FilterProductRequestDTO request) {
        Utente u = (Utente) token.getPrincipal();
        List<ProdottoReponseDTO> prodottoListOthers = utenteFacade.findAllOtherProducts(u, request);
        return ResponseEntity.status(HttpStatus.OK).body(prodottoListOthers);
    }

    /**
     * Endpoint per trovare tutti i propri messaggi.
     * @param token il token di autenticazione dell'utente
     * @return un ResponseEntity con la lista di tutti i propri messaggi
     */
    @GetMapping({"/venditore/findMyOwnMessage", "/cliente/findMyOwnMessage"})
    public ResponseEntity<List<MessaggioResponseDTO>> findMyOwnMessage(UsernamePasswordAuthenticationToken token) {
        Utente u = (Utente) token.getPrincipal();
        List<MessaggioResponseDTO> myOwnMessages = utenteFacade.findMyOwnMessage(u);
        return ResponseEntity.status(HttpStatus.OK).body(myOwnMessages);
    }

    /**
     * Endpoint per trovare tutti i propri feedback.
     * @param token il token di autenticazione dell'utente
     * @return un ResponseEntity con la lista di tutti i propri feedback
     */
    @GetMapping({"/venditore/findMyOwnFeedback", "/cliente/findMyOwnFeedback"})
    public ResponseEntity<List<FeedbackResponseDTO>> findMyOwnFeedback(UsernamePasswordAuthenticationToken token) {
        Utente u = (Utente) token.getPrincipal();
        List<FeedbackResponseDTO> myOwnMessages = utenteFacade.findMyOwnFeedback(u);
        return ResponseEntity.status(HttpStatus.OK).body(myOwnMessages);
    }

    // Funzionalità di tutti

    /**
     * Endpoint per la registrazione di un utente.
     * @param request i dati della richiesta di registrazione incapsulati in RegistrationUserRequestDTO
     * @return un ResponseEntity con i dettagli della registrazione se ha successo
     */
    @PostMapping("/all/registration")
    public ResponseEntity<RegistrationUserResponseDTO> registrazioneUtente(@Valid @RequestBody RegistrationUserRequestDTO request) {
        boolean registrato = utenteFacade.registrationUtente(request);
        if (registrato) {
            RegistrationUserResponseDTO registrationUserResponseDTO = new RegistrationUserResponseDTO.BuilderRegistrationUserDTO()
                    .setNome(request.getNome())
                    .setCognome(request.getCognome())
                    .setEmail(request.getEmail())
                    .setUsername(request.getUsername())
                    .setPassword(request.getPassword())
                    .setRuolo(request.getRuolo())
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(registrationUserResponseDTO);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Endpoint per il login di un utente.
     * @param request i dati della richiesta di login incapsulati in LoginRequestDTO
     * @return un ResponseEntity con i dettagli del login se ha successo
     */
    @PostMapping("/all/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        Utente u = utenteFacade.login(request);
        if (u != null) {
            LoginResponseDTO lr = new LoginResponseDTO.BuilderLoginResponseDTO()
                    .setUsername(request.getUsername())
                    .setRuolo(u.getRuolo().toString())
                    .build();
            String token = u.getToken();
            return ResponseEntity.status(HttpStatus.OK).header("Authorization", token).body(lr);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    /**
     * Endpoint per il logout di un utente.
     * @param token il token di autenticazione dell'utente
     * @return un ResponseEntity con un messaggio di successo o di errore
     */
    @GetMapping("/all/logout")
    public ResponseEntity<String> logout(UsernamePasswordAuthenticationToken token) {
        Utente u = (Utente) token.getPrincipal();
        boolean logout = utenteFacade.logout(u);
        if (logout) return ResponseEntity.status(HttpStatus.OK).body("Disconnesso con successo");
        else return ResponseEntity.badRequest().build();
    }
}

