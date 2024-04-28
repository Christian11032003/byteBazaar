package com.bytebazaar.bytebazaar.facade;

import com.bytebazaar.bytebazaar.exception.messaggiException.BadRequestException;
import com.bytebazaar.bytebazaar.model.Carrello;
import com.bytebazaar.bytebazaar.model.Utente;
import com.bytebazaar.bytebazaar.service.definition.CarrelloService;
import com.bytebazaar.bytebazaar.service.definition.OggettoCarrelloService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CarrelloFacade {
    private final CarrelloService carrelloService;

    private final OggettoCarrelloFacade oggettoCarrelloFacade;

    @Transactional
    public boolean confermaCarrello(Utente u){
        Carrello carrello = carrelloService.getByUsername(u.getUsername());
        if (carrello.getDataacquisto() != null) {
            throw new BadRequestException("Carrello non acquistato");
        }

        carrello.setDataacquisto(LocalDateTime.now());

        if (oggettoCarrelloFacade.modificaQuantitaRimanenti(u,carrello)) {
            carrelloService.confermaCarrello(carrello);
        }





        return true;
    }
}
