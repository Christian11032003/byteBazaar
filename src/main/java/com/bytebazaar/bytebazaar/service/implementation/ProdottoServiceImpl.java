package com.bytebazaar.bytebazaar.service.implementation;

import com.bytebazaar.bytebazaar.exception.messaggiException.NotFoundException;
import com.bytebazaar.bytebazaar.model.*;
import com.bytebazaar.bytebazaar.repository.ProdottoRepository;
import com.bytebazaar.bytebazaar.service.definition.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdottoServiceImpl implements ProdottoService
{

    @Autowired
    private ProdottoRepository prodottoRepo;


    @Override
    public Prodotto getByNome(String name) {
        return prodottoRepo.findByNome(name).orElseThrow(() -> new NotFoundException("Prodotto non trovato"));
    }

    @Override
    public void salva(Prodotto p) {
        prodottoRepo.save(p);
    }
}
