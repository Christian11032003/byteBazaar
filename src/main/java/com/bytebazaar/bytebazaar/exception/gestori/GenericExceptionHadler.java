package com.bytebazaar.bytebazaar.exception.gestori;

import com.bytebazaar.bytebazaar.dto.response.errori.MessaggioErroreResponseDTO;
import com.bytebazaar.bytebazaar.exception.messaggiException.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GenericExceptionHadler
{

    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity<MessaggioErroreResponseDTO> nonAutorizzato(UnAuthorizedException e) {
        MessaggioErroreResponseDTO m = new MessaggioErroreResponseDTO(HttpStatus.UNAUTHORIZED.name(),e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(m);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<MessaggioErroreResponseDTO> nonTrovato(NotFoundException e) {
        MessaggioErroreResponseDTO m = new MessaggioErroreResponseDTO(HttpStatus.NOT_FOUND.name(),e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(m);
    }

    @ExceptionHandler(AlreadyReportedException.class)
    public ResponseEntity<MessaggioErroreResponseDTO> richiestaGiàDefinita(AlreadyReportedException e) {
        MessaggioErroreResponseDTO m = new MessaggioErroreResponseDTO(HttpStatus.ALREADY_REPORTED.name(),e.getMessage());
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(m);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<MessaggioErroreResponseDTO> bruttaRichiesta(BadRequestException e) {
        MessaggioErroreResponseDTO m = new MessaggioErroreResponseDTO(HttpStatus.BAD_REQUEST.name(),e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);
    }

    @ExceptionHandler(SwitchingProtocolException.class)
    public ResponseEntity<MessaggioErroreResponseDTO> cambiaHeader(SwitchingProtocolException e) {
        MessaggioErroreResponseDTO m = new MessaggioErroreResponseDTO(HttpStatus.SWITCHING_PROTOCOLS.name(),e.getMessage());
        return ResponseEntity.status(HttpStatus.SWITCHING_PROTOCOLS).body(m);
    }






}
