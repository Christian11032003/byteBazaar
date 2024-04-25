package com.bytebazaar.bytebazaar.service.implementation;

import com.bytebazaar.bytebazaar.exception.messaggiException.NotFoundException;
import com.bytebazaar.bytebazaar.model.*;
import com.bytebazaar.bytebazaar.repository.*;
import com.bytebazaar.bytebazaar.service.definition.OggettoCarrelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OggettoCarrelloServiceImpl implements OggettoCarrelloService
{
    @Autowired
    private OggettocarrelloRepository oggettocarrelloRepo;


    @Override
    public void salva(OggettoCarrello o) {
        oggettocarrelloRepo.save(o);
    }

    @Override
    public OggettoCarrello getByCarrelloIdcarrelloAndProdottoIdProdotto(int idCarrello, int idProdotto) {
        return oggettocarrelloRepo.findByCarrello_IdcarrelloAndProdotto_IdProdotto(idCarrello,idProdotto).orElseThrow(() -> new NotFoundException("Prodotto non trovato"));
    }

    @Override
    public OggettoCarrello getById(int idOggettoCarrello) {
        return oggettocarrelloRepo.findById(idOggettoCarrello).orElseThrow(() -> new NotFoundException("OggettoCarrello non trovato"));
    }

    @Override
    public void cancella(OggettoCarrello o) {
        oggettocarrelloRepo.delete(o);
    }
}
