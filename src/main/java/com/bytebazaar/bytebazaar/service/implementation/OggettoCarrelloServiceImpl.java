package com.bytebazaar.bytebazaar.service.implementation;


import com.bytebazaar.bytebazaar.model.*;
import com.bytebazaar.bytebazaar.repository.*;
import com.bytebazaar.bytebazaar.service.definition.OggettoCarrelloService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

// La classe implementa l'interfaccia OggettoCarrelloService, fornendo l'implementazione dei metodi definiti nell'interfaccia.
@Service
@RequiredArgsConstructor
public class OggettoCarrelloServiceImpl implements OggettoCarrelloService {

    // Il OggettocarrelloRepository è una dipendenza di questo servizio, utilizzata per le operazioni di accesso ai dati relative alle entità 'Oggettocarrello'.
    // È dichiarato come final per garantire che, una volta inizializzato, non possa essere riassegnato.
    private final OggettocarrelloRepository oggettocarrelloRepo;

    /**
     * Salva un'istanza di Oggettocarrello nel repository.
     *
     * @param o l'istanza di Oggettocarrello da salvare
     */
    @Override
    public void salva(Oggettocarrello o) {
        oggettocarrelloRepo.save(o);
    }

    /**
     * Recupera un'istanza di Oggettocarrello basata su idCarrello e idProdotto.
     *
     * @param idCarrello id del carrello
     * @param idProdotto id del prodotto
     * @return un Optional contenente l'istanza di Oggettocarrello, se presente
     */
    @Override
    public Optional<Oggettocarrello> getByCarrelloIdcarrelloAndProdottoIdProdotto(int idCarrello, int idProdotto) {
        return oggettocarrelloRepo.findByCarrello_IdAndProdotto_Id(idCarrello, idProdotto);
    }

    /**
     * Recupera un'istanza di Oggettocarrello basata su idOggettoCarrello.
     *
     * @param idOggettoCarrello l'ID dell'Oggettocarrello da recuperare
     * @return un Optional contenente l'istanza di Oggettocarrello, se presente
     */
    @Override
    public Optional<Oggettocarrello> getById(int idOggettoCarrello) {
        return oggettocarrelloRepo.findById(idOggettoCarrello);
    }

    /**
     * Cancella un'istanza di Oggettocarrello dal repository.
     *
     * @param o l'istanza di Oggettocarrello da cancellare
     */
    @Override
    public void cancella(Oggettocarrello o) {
        oggettocarrelloRepo.delete(o);
    }
}
