package com.bytebazaar.bytebazaar.service.implementation;

import com.bytebazaar.bytebazaar.exception.messaggiException.BadRequestException;
import com.bytebazaar.bytebazaar.model.Ruolo;
import com.bytebazaar.bytebazaar.model.Utente;
import com.bytebazaar.bytebazaar.repository.UtenteRepository;
import com.bytebazaar.bytebazaar.service.definition.UtenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


// La classe implementa l'interfaccia UtenteService, fornendo l'implementazione dei metodi definiti nell'interfaccia.
@Service
@RequiredArgsConstructor
public class UtenteServiceImpl implements UtenteService
{


    // Il UtenteRepository è una dipendenza di questo servizio, utilizzata per le operazioni di accesso ai dati relative alle entità 'Utente'.
    private final UtenteRepository utenteRepo;

    /**
     * Recupera un'istanza di Utente basata sul nome utente.
     *
     * @param username il nome utente per cercare l'utente
     * @return l'istanza di Utente corrispondente al nome utente specificato
     * @throws BadRequestException se l'utente con il nome utente specificato non viene trovato
     */
    @Override
    public Utente getByUsername(String username) {
        return utenteRepo.findByUsername(username).orElseThrow(() -> new BadRequestException("utente non trovato"));
    }

    /**
     * Recupera un'istanza di Utente basata sul suo ID.
     *
     * @param id l'ID dell'utente da recuperare
     * @return l'istanza di Utente corrispondente all'ID specificato
     * @throws BadRequestException se l'utente con l'ID specificato non viene trovato
     */
    @Override
    public Utente getById(int id) {
        return utenteRepo.findById(id).orElseThrow(() -> new BadRequestException("utente non trovato"));
    }

    /**
     * Recupera una lista di utenti basata sul ruolo.
     *
     * @param ruolo il ruolo degli utenti da recuperare
     * @return una lista di utenti corrispondenti al ruolo specificato
     */
    @Override
    public List<Utente> findAllByRuolo(Ruolo ruolo) {
        return utenteRepo.findAllByRuolo(ruolo);
    }

    /**
     * Salva un'istanza di Utente nel repository.
     *
     * @param u l'istanza di Utente da salvare
     */
    @Override
    public void salva(Utente u) {
        utenteRepo.save(u);
    }





}
