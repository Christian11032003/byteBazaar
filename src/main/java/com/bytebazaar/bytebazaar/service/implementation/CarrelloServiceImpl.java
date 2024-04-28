package com.bytebazaar.bytebazaar.service.implementation;


import com.bytebazaar.bytebazaar.exception.messaggiException.BadRequestException;
import com.bytebazaar.bytebazaar.model.Carrello;
import com.bytebazaar.bytebazaar.repository.CarrelloRepository;
import com.bytebazaar.bytebazaar.service.definition.CarrelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
public class CarrelloServiceImpl implements CarrelloService
{

    @Autowired
    CarrelloRepository carrelloRepo;

    @Override
    public Carrello getByUsername(String username) {
        return carrelloRepo.findCarrelloByUtente_UsernameAndDataacquistoIsNull(username).orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST,"nessun carrello per questo utente"));
    }

    @Override
    public void confermaCarrello(Carrello c) {
        if(c.getDataacquisto()!=null) throw new BadRequestException("Carrello non acquistato");
        c.setDataacquisto(LocalDateTime.now());
        carrelloRepo.save(c);
    }


}
