package com.bytebazaar.bytebazaar.exception.gestori;

import com.bytebazaar.bytebazaar.dto.response.errori.MessaggioErroreResponse;
import com.bytebazaar.bytebazaar.exception.messaggiException.exceptionRichiesta.MessaggioRichiestaAlreadyDefined;
import com.bytebazaar.bytebazaar.exception.messaggiException.exceptionRichiesta.MessaggioRichiestaNotFoundException;
import com.bytebazaar.bytebazaar.exception.messaggiException.exceptionUtente.MessaggioUtenteNotFoundException;
import com.bytebazaar.bytebazaar.exception.messaggiException.exceptionUtente.MessaggioUtenteUnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GenericExceptionHadler
{
    //Messaggi Utente
    @ExceptionHandler(MessaggioUtenteUnauthorizedException.class)
    public ResponseEntity<MessaggioErroreResponse> utenteNonAutorizzato(MessaggioUtenteUnauthorizedException e) {
        MessaggioErroreResponse m = new MessaggioErroreResponse(HttpStatus.UNAUTHORIZED.name(),e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(m);
    }

    @ExceptionHandler(MessaggioUtenteNotFoundException.class)
    public ResponseEntity<MessaggioErroreResponse> utenteNonTrovato(MessaggioUtenteNotFoundException e) {
        MessaggioErroreResponse m = new MessaggioErroreResponse(HttpStatus.NOT_FOUND.name(),e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(m);
    }

    @ExceptionHandler(MessaggioRichiestaAlreadyDefined.class)
    public ResponseEntity<MessaggioErroreResponse> richiestaGiàDefinita(MessaggioRichiestaAlreadyDefined e)
    {
        MessaggioErroreResponse m = new MessaggioErroreResponse(HttpStatus.ALREADY_REPORTED.name(),e.getMessage());
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(m);
    }

    @ExceptionHandler(MessaggioRichiestaNotFoundException.class)
    public ResponseEntity<MessaggioErroreResponse> messaggioRichiestaNonTrovato(MessaggioRichiestaNotFoundException e)
    {
        MessaggioErroreResponse m = new MessaggioErroreResponse(HttpStatus.NOT_FOUND.name(),e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(m);
    }


}
