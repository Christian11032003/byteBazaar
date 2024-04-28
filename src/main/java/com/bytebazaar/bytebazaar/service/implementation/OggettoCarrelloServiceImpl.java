package com.bytebazaar.bytebazaar.service.implementation;

import com.bytebazaar.bytebazaar.exception.messaggiException.NotFoundException;
import com.bytebazaar.bytebazaar.model.*;
import com.bytebazaar.bytebazaar.repository.*;
import com.bytebazaar.bytebazaar.service.definition.OggettoCarrelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OggettoCarrelloServiceImpl implements OggettoCarrelloService
{
    @Autowired
    private OggettocarrelloRepository oggettocarrelloRepo;


    @Override
    public void salva(Oggettocarrello o) {
        oggettocarrelloRepo.save(o);
    }

    @Override
    public Oggettocarrello getByCarrelloIdcarrelloAndProdottoIdProdotto(int idCarrello, int idProdotto) {
        return oggettocarrelloRepo.findByCarrello_IdAndProdotto_Id(idCarrello,idProdotto).orElseThrow(() -> new NotFoundException("Prodotto non trovato"));
    }

    @Override
    public Oggettocarrello getById(int idOggettoCarrello) {
        return oggettocarrelloRepo.findById(idOggettoCarrello).orElseThrow(() -> new NotFoundException("OggettoCarrello non trovato"));
    }

    @Override
    public void cancella(Oggettocarrello o) {
        oggettocarrelloRepo.delete(o);
    }
}
