package com.bytebazaar.bytebazaar.controller;

import com.bytebazaar.bytebazaar.dto.request.AddProductToCartRequestDTO;
import com.bytebazaar.bytebazaar.dto.request.DeleteObjectFromCartRequestDTO;
import com.bytebazaar.bytebazaar.dto.request.SubtractQuantityRequestDTO;
import com.bytebazaar.bytebazaar.dto.response.OggettoCarrelloReponseDTO;
import com.bytebazaar.bytebazaar.facade.OggettoCarrelloFacade;
import com.bytebazaar.bytebazaar.model.Utente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


//Questa classe OggettoCarrelloController gestisce le operazioni relative agli oggetti nel carrello di un utente
@RestController
@RequiredArgsConstructor
public class OggettoCarrelloController
{

    // Dipendenza da OggettoCarrelloFacade, utilizzata per gestire la logica di business relativa agli oggetti nel carrello.
    private final OggettoCarrelloFacade oggettoCarrelloFacade;

    /**
     * @param token il token di autenticazione contenente il token dell'utente.
     * @param request il corpo della richiesta contenente i dettagli dell'oggetto da aggiungere al carrello.
     * @return un ResponseEntity con uno stato HTTP appropriato e il corpo del messaggio.
     */
    @PostMapping({"/venditore/aggiungiOggettoCarrello","/cliente/aggiungiOggettoCarrello"})
    public ResponseEntity<OggettoCarrelloReponseDTO> aggiungiOggettoCarrello(UsernamePasswordAuthenticationToken token, @RequestBody AddProductToCartRequestDTO request) {
        // Estrae l'utente (Utente) dal token di autenticazione.
        Utente u = (Utente) token.getPrincipal();

        // Tenta di aggiungere l'oggetto al carrello per l'utente.
        boolean registratoOggettoCarrello = oggettoCarrelloFacade.aggiungiAlCarrello(u, request);

        // Se l'oggetto è stato aggiunto con successo, costruisce la risposta.
        if (registratoOggettoCarrello) {
            OggettoCarrelloReponseDTO oggettoCarrelloReponseDTO = new OggettoCarrelloReponseDTO.BuilderOggettoCarrelloDTO()
                    .setId(request.getIdProdotto())
                    .setQuantita(request.getQuantita())
                    .build();

            return ResponseEntity.status(HttpStatus.OK).body(oggettoCarrelloReponseDTO);
        } else {
            // Se l'oggetto non è stato aggiunto, restituisce un Bad Request.
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * @param request il corpo della richiesta contenente i dettagli dell'oggetto e della quantità da sottrarre.
     * @return un ResponseEntity con uno stato HTTP appropriato e il corpo del messaggio.
     */
    @PostMapping({"/venditore/sottraiQuantita","/cliente/sottraiQuantita"})
    public ResponseEntity<String> sottraiquantita(@RequestBody SubtractQuantityRequestDTO request) {
        // Tenta di sottrarre la quantità dell'oggetto nel carrello.
        boolean sottraiQuantita = oggettoCarrelloFacade.sottraiQuantita(request);

        // Se la quantità è stata sottratta con successo, restituisce una risposta con stato OK.
        if (sottraiQuantita) {
            return ResponseEntity.status(HttpStatus.OK).body("Quantità modificata con successo");
        } else {
            // Se la sottrazione della quantità non è riuscita, restituisce un Bad Request.
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * @param request il corpo della richiesta contenente i dettagli dell'oggetto da eliminare dal carrello.
     * @return un ResponseEntity con uno stato HTTP appropriato e il corpo del messaggio.
     */
    @PostMapping({"/venditore/eliminaOggettoCarrello","/cliente/eliminaOggettoCarrello"})
    public ResponseEntity<String> eliminaoggettocarrello(@RequestBody DeleteObjectFromCartRequestDTO request) {
        // Tenta di eliminare l'oggetto dal carrello.
        boolean eliminato = oggettoCarrelloFacade.eliminaoggettocarrello(request);

        // Se l'oggetto è stato eliminato con successo, restituisce una risposta con stato OK.
        if (eliminato) {
            return ResponseEntity.status(HttpStatus.OK).body("Prodotto eliminato dal carrello");
        } else {
            // Se l'eliminazione dell'oggetto non è riuscita, restituisce un Bad Request.
            return ResponseEntity.badRequest().build();
        }
    }


}
