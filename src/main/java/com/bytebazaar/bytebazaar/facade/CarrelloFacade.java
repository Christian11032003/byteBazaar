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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarrelloFacade {
    private final CarrelloService carrelloService;

    private final OggettoCarrelloFacade oggettoCarrelloFacade;


    public boolean confermaCarrello(Utente u){
        Optional<Carrello> carrello = carrelloService.getByUsername(u.getUsername());


        if(carrello.isPresent())
        {

            Carrello c = carrello.get();


            if (c.getDataacquisto() != null) {
                throw new BadRequestException("Carrello non acquistato");
            }

            c.setDataacquisto(LocalDateTime.now());

            if (oggettoCarrelloFacade.modificaQuantitaRimanenti(u, c))
            {

                carrelloService.salva(c);
            }

            return true;

        }

        throw new BadRequestException("Carrello non trovato");
    }
}
