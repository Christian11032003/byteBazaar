package com.bytebazaar.bytebazaar.service.implementation;


import com.bytebazaar.bytebazaar.exception.messaggiException.BadRequestException;
import com.bytebazaar.bytebazaar.model.Carrello;
import com.bytebazaar.bytebazaar.repository.CarrelloRepository;
import com.bytebazaar.bytebazaar.service.definition.CarrelloService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CarrelloServiceImpl implements CarrelloService
{

    @Autowired
    CarrelloRepository carrelloRepo;

    @Override
    public Optional<Carrello> getByUsername(String username) {
        return carrelloRepo.findCarrelloByUtente_UsernameAndDataacquistoIsNull(username);
    }

    @Override
    public void salva(Carrello c) {

        carrelloRepo.save(c);
    }


}
