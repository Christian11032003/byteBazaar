package com.bytebazaar.bytebazaar.controller;

import com.bytebazaar.bytebazaar.dto.request.AddFeedbackRequestDTO;
import com.bytebazaar.bytebazaar.dto.request.ModifyFeedbackRequestDTO;
import com.bytebazaar.bytebazaar.dto.response.FeedbackResponseDTO;
import com.bytebazaar.bytebazaar.facade.FeedbackFacade;
import com.bytebazaar.bytebazaar.model.Utente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//La classe FeedbackController gestisce le operazioni relative all'invio dei feedback e alla modifica.
@RestController
@RequiredArgsConstructor
public class FeedbackController
{

    // Dipendenza da FeedbackFacade, utilizzata per gestire la logica di business relativa ai feedback.
    private final FeedbackFacade feedbackFacade;

    /**
     * @param token il token di autenticazione contenente il token dell'utente.
     * @param request il corpo della richiesta contenente i dettagli del feedback.
     * @return un ResponseEntity con uno stato HTTP appropriato e il Feedback mandato.
     */
    @PostMapping({"/venditore/mandaFeedback", "/cliente/mandaFeedback"})
    public ResponseEntity<FeedbackResponseDTO> mandaFeedback(UsernamePasswordAuthenticationToken token, @RequestBody AddFeedbackRequestDTO request) {
        // Estrae l'utente (Utente) dal token di autenticazione.
        Utente u = (Utente) token.getPrincipal();

        // Tenta di aggiungere il feedback per l'utente.
        boolean addFeedback = feedbackFacade.aggiungiFeedback(u, request);

        // Se il feedback è stato aggiunto con successo, costruisce la risposta.
        if (addFeedback) {
            FeedbackResponseDTO feedbackResponseDTO = new FeedbackResponseDTO.BuilderFeedbackResponseDTO()
                    .setIdProdotto(request.getIdProdotto())
                    .setDescrizione(request.getDescrizione())
                    .setValutazione(request.getValutazione())
                    .build();

            return ResponseEntity.status(HttpStatus.OK).body(feedbackResponseDTO);
        } else {
            // Se il feedback non è stato aggiunto, restituisce una Bad Request.
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * @param request il corpo della richiesta contenente i dettagli del feedback da modificare.
     * @return un ResponseEntity con uno stato HTTP appropriato e il Feedback modificato.
     */
    @PostMapping({"/venditore/modificaFeedback", "/cliente/modificaFeedback"})
    public ResponseEntity<String> modificaFeedback(@RequestBody ModifyFeedbackRequestDTO request) {
        // Tenta di modificare il feedback.
        boolean modifyFeedback = feedbackFacade.modificaFeedback(request);

        // Se il feedback è stato modificato con successo, restituisce una risposta con stato OK.
        if (modifyFeedback) {
            return ResponseEntity.status(HttpStatus.OK).body("Feedback modificato con successo");
        } else {
            // Se la modifica del feedback non è riuscita, restituisce un Bad Request.
            return ResponseEntity.badRequest().build();
        }
    }




}
