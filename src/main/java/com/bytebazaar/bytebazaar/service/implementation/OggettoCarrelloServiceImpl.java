package com.bytebazaar.bytebazaar.service.implementation;

import com.bytebazaar.bytebazaar.exception.messaggiException.NotFoundException;
import com.bytebazaar.bytebazaar.model.*;
import com.bytebazaar.bytebazaar.repository.*;
import com.bytebazaar.bytebazaar.service.definition.OggettoCarrelloService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OggettoCarrelloServiceImpl implements OggettoCarrelloService
{

    private final OggettocarrelloRepository oggettocarrelloRepo;


    @Override
    public void salva(Oggettocarrello o) {
        oggettocarrelloRepo.save(o);
    }

    @Override
    public Optional<Oggettocarrello> getByCarrelloIdcarrelloAndProdottoIdProdotto(int idCarrello, int idProdotto) {
        return oggettocarrelloRepo.findByCarrello_IdAndProdotto_Id(idCarrello,idProdotto);
    }

    @Override
    public Optional<Oggettocarrello> getById(int idOggettoCarrello) {
        return oggettocarrelloRepo.findById(idOggettoCarrello);
    }

    @Override
    public void cancella(Oggettocarrello o) {
        oggettocarrelloRepo.delete(o);
    }
}
