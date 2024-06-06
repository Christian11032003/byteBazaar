package com.bytebazaar.bytebazaar.service.implementation;

import com.bytebazaar.bytebazaar.exception.messaggiException.NotFoundException;
import com.bytebazaar.bytebazaar.model.*;
import com.bytebazaar.bytebazaar.repository.ProdottoRepository;
import com.bytebazaar.bytebazaar.service.definition.ProdottoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// La classe implementa l'interfaccia ProdottoService, fornendo l'implementazione dei metodi definiti nell'interfaccia.
@Service
@RequiredArgsConstructor
public class ProdottoServiceImpl implements ProdottoService
{


    // Il ProdottoRepository è una dipendenza di questo servizio, utilizzata per le operazioni di accesso ai dati relative alle entità 'Prodotto'.
    private final ProdottoRepository prodottoRepo;


    /**
     * Recupera un'istanza di Prodotto basata sul nome.
     *
     * @param name il nome del prodotto da recuperare
     * @return l'istanza di Prodotto corrispondente al nome specificato
     * @throws NotFoundException se il prodotto con il nome specificato non viene trovato
     */
    @Override
    public Prodotto getByNome(String name) {
        return prodottoRepo.findByNome(name).orElseThrow(() -> new NotFoundException("Prodotto non trovato"));
    }

    /**
     * Salva un'istanza di Prodotto nel repository.
     *
     * @param p l'istanza di Prodotto da salvare
     */
    @Override
    public void salva(Prodotto p) {
        prodottoRepo.save(p);
    }
}
