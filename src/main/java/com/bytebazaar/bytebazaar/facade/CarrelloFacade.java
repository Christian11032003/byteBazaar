package com.bytebazaar.bytebazaar.facade;

import com.bytebazaar.bytebazaar.dto.response.ProdottoDTO;
import com.bytebazaar.bytebazaar.exception.messaggiException.BadRequestException;
import com.bytebazaar.bytebazaar.model.Carrello;
import com.bytebazaar.bytebazaar.model.Utente;
import com.bytebazaar.bytebazaar.service.definition.CarrelloService;
import com.bytebazaar.bytebazaar.service.definition.OggettocarrelloService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CarrelloFacade {
    private final CarrelloService carrelloService;

    private final OggettocarrelloService serviceOggettoCarrello;

    @Transactional
    public boolean confermaCarrello(Utente u){
        Carrello carrello = carrelloService.getByUsername(u.getUsername());
        if (carrello.getDataAcquisto() != null) {
            throw new BadRequestException("Carrello non acquistato");
        }

        carrello.setDataAcquisto(LocalDateTime.now());

        if (serviceOggettoCarrello.modificaQuantitaRimanenti(u,carrello)) {
            carrelloService.confermaCarrello(carrello);
        }


        /*ProdottoDTO p=new ProdottoDTO.Builder()
                .setDescrizione("csdvcsdf")
                .setIdProdotto(4)
                .build();*/

        //DTO response del utente per il builder


        return true;
    }
}
