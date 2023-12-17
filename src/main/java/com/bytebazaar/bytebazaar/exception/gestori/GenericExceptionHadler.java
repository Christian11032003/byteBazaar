package com.bytebazaar.bytebazaar.exception.gestori;

import com.bytebazaar.bytebazaar.dto.response.errori.MessaggioErroreResponse;
import com.bytebazaar.bytebazaar.exception.exceptionUtente.MessaggioUtenteNotFoundException;
import com.bytebazaar.bytebazaar.exception.exceptionUtente.MessaggioUtenteUnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GenericExceptionHadler
{
    @ExceptionHandler(MessaggioUtenteUnauthorizedException.class)
    public ResponseEntity<MessaggioErroreResponse> utenteNonAutorizzato(MessaggioUtenteUnauthorizedException e) {
        MessaggioErroreResponse m = new MessaggioErroreResponse(HttpStatus.UNAUTHORIZED.name(),e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(m);
    }

    @ExceptionHandler(MessaggioUtenteNotFoundException.class)
    public ResponseEntity<MessaggioErroreResponse> utenteNonTrovato(MessaggioUtenteUnauthorizedException e) {
        MessaggioErroreResponse m = new MessaggioErroreResponse(HttpStatus.NOT_FOUND.name(),e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(m);
    }

}
