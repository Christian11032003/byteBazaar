package com.bytebazaar.bytebazaar.controller;

import com.bytebazaar.bytebazaar.dto.request.prodotto.InsertOrModifyProductRequestDTO;
import com.bytebazaar.bytebazaar.dto.response.ProdottoDTO;
import com.bytebazaar.bytebazaar.facade.ProdottoFacade;
import com.bytebazaar.bytebazaar.model.Prodotto;
import com.bytebazaar.bytebazaar.model.Utente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProdottoController
{

    private final ProdottoFacade prodottoFacade;




    //funzionalità del venditore
    @PostMapping("/venditore/registraProdotto")
    public ResponseEntity<ProdottoDTO> registrazioneProdotto(UsernamePasswordAuthenticationToken token, @RequestBody InsertOrModifyProductRequestDTO request) {
        Utente u = (Utente)token.getPrincipal();
        boolean registrato = prodottoFacade.registraProdotto(u,request);
        if (registrato)
        {

            ProdottoDTO p = new ProdottoDTO.Builder()
                    .setImmagineProdotto(request.getImmagine())
                    .setNome(request.getNome())
                    .setDescrizione(request.getDescrizione())
                    .setQuantita(request.getQuantita())
                    .setPrezzo(request.getQuantita())
                    .build();


            return ResponseEntity.status(HttpStatus.OK).body(p);
        }

        else
        {

            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/venditore/modificaProdotto")
    public ResponseEntity<ProdottoDTO> modificaProdotto(UsernamePasswordAuthenticationToken token,@RequestBody InsertOrModifyProductRequestDTO request){
        Utente u = (Utente)token.getPrincipal();
        boolean registrato = prodottoFacade.modificaProdotto(u,request);
        if (registrato)
        {
            ProdottoDTO p = new ProdottoDTO.Builder()
                    .setImmagineProdotto(request.getImmagine())
                    .setNome(request.getNome())
                    .setDescrizione(request.getDescrizione())
                    .setQuantita(request.getQuantita())
                    .setPrezzo(request.getPrezzo())
                    .build();

            return ResponseEntity.status(HttpStatus.OK).body(p);
        }
        else return ResponseEntity.badRequest().build();
    }



}
