package com.bytebazaar.bytebazaar.exception.gestori;

import com.bytebazaar.bytebazaar.dto.response.errori.MessaggioErroreResponse;
import com.bytebazaar.bytebazaar.exception.messaggiException.AlreadyReportedException;
import com.bytebazaar.bytebazaar.exception.messaggiException.NotFoundException;
import com.bytebazaar.bytebazaar.exception.messaggiException.UnAuthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GenericExceptionHadler
{
    //Messaggi Utente
    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity<MessaggioErroreResponse> nonAutorizzato(UnAuthorizedException e) {
        MessaggioErroreResponse m = new MessaggioErroreResponse(HttpStatus.UNAUTHORIZED.name(),e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(m);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<MessaggioErroreResponse> nonTrovato(NotFoundException e) {
        MessaggioErroreResponse m = new MessaggioErroreResponse(HttpStatus.NOT_FOUND.name(),e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(m);
    }

    @ExceptionHandler(AlreadyReportedException.class)
    public ResponseEntity<MessaggioErroreResponse> richiestaGiàDefinita(AlreadyReportedException e) {
        MessaggioErroreResponse m = new MessaggioErroreResponse(HttpStatus.ALREADY_REPORTED.name(),e.getMessage());
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(m);
    }



}
