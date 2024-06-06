package com.bytebazaar.bytebazaar.service.implementation;

import com.bytebazaar.bytebazaar.exception.messaggiException.BadRequestException;
import com.bytebazaar.bytebazaar.model.Richiesta;
import com.bytebazaar.bytebazaar.repository.RichiestaRepository;
import com.bytebazaar.bytebazaar.service.definition.RichiestaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

// La classe implementa l'interfaccia RichiestaService, fornendo l'implementazione dei metodi definiti nell'interfaccia.
@Service
@RequiredArgsConstructor
public class RichiestaServiceImpl implements RichiestaService
{


    // Il RichiestaRepository è una dipendenza di questo servizio, utilizzata per le operazioni di accesso ai dati relative alle entità 'Richiesta'.
    private final RichiestaRepository richiestaRepo;


    /**
     * Recupera un'istanza di Richiesta basata sul nome utente.
     *
     * @param username il nome utente per cercare la richiesta
     * @return un Optional contenente l'istanza di Richiesta, se presente
     */
    @Override
    public Optional<Richiesta> findByUtenteUsername(String username) {
        return richiestaRepo.findByUtente_Username(username);
    }

    /**
     * Salva un'istanza di Richiesta nel repository.
     *
     * @param r l'istanza di Richiesta da salvare
     */
    @Override
    public void salva(Richiesta r) {
        richiestaRepo.save(r);
    }

    /**
     * Recupera un'istanza di Richiesta basata sul suo ID.
     *
     * @param idRichiesta l'ID della richiesta da recuperare
     * @return l'istanza di Richiesta corrispondente all'ID specificato
     * @throws BadRequestException se la richiesta con l'ID specificato non viene trovata
     */
    @Override
    public Richiesta findByIdrichiesta(int idRichiesta) {
        return richiestaRepo.findById(idRichiesta).orElseThrow(() -> new BadRequestException("Richiesta non trovata"));
    }
}
