package com.bytebazaar.bytebazaar.service.implementation;


import com.bytebazaar.bytebazaar.exception.messaggiException.BadRequestException;
import com.bytebazaar.bytebazaar.exception.messaggiException.NotFoundException;
import com.bytebazaar.bytebazaar.model.Carrello;
import com.bytebazaar.bytebazaar.model.Utente;
import com.bytebazaar.bytebazaar.repository.CarrelloRepository;
import com.bytebazaar.bytebazaar.service.definition.CarrelloService;
import com.bytebazaar.bytebazaar.service.definition.OggettocarrelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CarrelloServiceImpl implements CarrelloService
{

    @Autowired
    CarrelloRepository carrelloRepo;

    @Autowired
    OggettocarrelloService serviceOggettoCarrello;


    public boolean confermaCarrello(Utente u) {
        Optional<Carrello> carrelloOptional = carrelloRepo.findCarrelloByUtente_UsernameAndUtente_PasswordAndDataAcquistoIsNull(u.getUsername(), u.getPassword());

        if (carrelloOptional.isEmpty()) {
            throw new NotFoundException("Carrello non trovato");
        }

        Carrello c = carrelloOptional.get();

        if (c.getDataAcquisto() != null) {
            throw new BadRequestException("Carrello non acquistato");
        }

        c.setDataAcquisto(LocalDateTime.now());

        if (serviceOggettoCarrello.modificaQuantitaRimanenti(u,c)) {
            carrelloRepo.save(c);
        }

        return true;
    }



}
