package com.bytebazaar.bytebazaar.service.implementation;

import com.bytebazaar.bytebazaar.dto.request.CreaCarrelloRequest;
import com.bytebazaar.bytebazaar.dto.request.LoginRequest;
import com.bytebazaar.bytebazaar.exception.messaggiException.BadRequestException;
import com.bytebazaar.bytebazaar.exception.messaggiException.NotFoundException;
import com.bytebazaar.bytebazaar.model.Carrello;
import com.bytebazaar.bytebazaar.model.Oggettocarrello;
import com.bytebazaar.bytebazaar.model.Ruolo;
import com.bytebazaar.bytebazaar.model.Utente;
import com.bytebazaar.bytebazaar.repository.CarrelloRepository;
import com.bytebazaar.bytebazaar.service.definition.CarrelloService;
import com.bytebazaar.bytebazaar.service.definition.OggettocarrelloService;
import com.bytebazaar.bytebazaar.utils.Util;
import lombok.SneakyThrows;
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

    @SneakyThrows
    public boolean confermaCarrello(LoginRequest request) {
        Optional<Carrello> carrelloOptional = carrelloRepo.findCarrelloByUtente_UsernameAndUtente_PasswordAndDataAcquistoIsNull(request.getUsername(), request.getPassword());

        if (carrelloOptional.isEmpty()) {
            throw new NotFoundException("Carrello non trovato");
        }

        Carrello c = carrelloOptional.get();

        if (c.getDataAcquisto() != null) {
            throw new BadRequestException("Carrello non acquistato");
        }

        c.setDataAcquisto(LocalDateTime.now());

        if (serviceOggettoCarrello.modificaQuantitaRimanenti(request, c)) {
            carrelloRepo.save(c);
        }

        return true;
    }



}
