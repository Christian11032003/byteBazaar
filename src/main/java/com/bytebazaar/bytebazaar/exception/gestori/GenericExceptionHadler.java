package com.bytebazaar.bytebazaar.exception.gestori;

import com.bytebazaar.bytebazaar.dto.response.errori.MessaggioErroreResponseDTO;
import com.bytebazaar.bytebazaar.exception.messaggiException.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GenericExceptionHadler {
    /**
     * Gestisce un'eccezione NotFoundException, restituendo una risposta con status NOT_FOUND.
     *
     * @param e NotFoundException lanciata
     * @return ResponseEntity contenente un MessaggioErroreResponseDTO con le informazioni sull'errore
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<MessaggioErroreResponseDTO> nonTrovato(NotFoundException e) {
        // Costruisce un MessaggioErroreResponseDTO con lo status e il messaggio dell'eccezione
        MessaggioErroreResponseDTO m = new MessaggioErroreResponseDTO(HttpStatus.NOT_FOUND.name(), e.getMessage());
        // Restituisce una ResponseEntity con status NOT_FOUND e il MessaggioErroreResponseDTO
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(m);
    }

    /**
     * Gestisce un'eccezione AlreadyReportedException, restituendo una risposta con status ALREADY_REPORTED.
     *
     * @param e AlreadyReportedException lanciata
     * @return ResponseEntity contenente un MessaggioErroreResponseDTO con le informazioni sull'errore
     */
    @ExceptionHandler(AlreadyReportedException.class)
    public ResponseEntity<MessaggioErroreResponseDTO> richiestaGiàDefinita(AlreadyReportedException e) {
        // Costruisce un MessaggioErroreResponseDTO con lo status e il messaggio dell'eccezione
        MessaggioErroreResponseDTO m = new MessaggioErroreResponseDTO(HttpStatus.ALREADY_REPORTED.name(), e.getMessage());
        // Restituisce una ResponseEntity con status ALREADY_REPORTED e il MessaggioErroreResponseDTO
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(m);
    }

    /**
     * Gestisce un'eccezione BadRequestException, restituendo una risposta con status BAD_REQUEST.
     *
     * @param e BadRequestException lanciata
     * @return ResponseEntity contenente un MessaggioErroreResponseDTO con le informazioni sull'errore
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<MessaggioErroreResponseDTO> bruttaRichiesta(BadRequestException e) {
        // Costruisce un MessaggioErroreResponseDTO con lo status e il messaggio dell'eccezione
        MessaggioErroreResponseDTO m = new MessaggioErroreResponseDTO(HttpStatus.BAD_REQUEST.name(), e.getMessage());
        // Restituisce una ResponseEntity con status BAD_REQUEST e il MessaggioErroreResponseDTO
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);
    }

    /**
     * Gestisce un'eccezione SwitchingProtocolException, restituendo una risposta con status SWITCHING_PROTOCOLS.
     *
     * @param e SwitchingProtocolException lanciata
     * @return ResponseEntity contenente un MessaggioErroreResponseDTO con le informazioni sull'errore
     */
    @ExceptionHandler(SwitchingProtocolException.class)
    public ResponseEntity<MessaggioErroreResponseDTO> cambiaHeader(SwitchingProtocolException e) {
        // Costruisce un MessaggioErroreResponseDTO con lo status e il messaggio dell'eccezione
        MessaggioErroreResponseDTO m = new MessaggioErroreResponseDTO(HttpStatus.SWITCHING_PROTOCOLS.name(), e.getMessage());
        // Restituisce una ResponseEntity con status SWITCHING_PROTOCOLS e il MessaggioErroreResponseDTO
        return ResponseEntity.status(HttpStatus.SWITCHING_PROTOCOLS).body(m);
    }
}


