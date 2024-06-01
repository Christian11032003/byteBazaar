package com.bytebazaar.bytebazaar.service.implementation;

import com.bytebazaar.bytebazaar.exception.messaggiException.BadRequestException;
import com.bytebazaar.bytebazaar.model.Ruolo;
import com.bytebazaar.bytebazaar.model.Utente;
import com.bytebazaar.bytebazaar.repository.UtenteRepository;
import com.bytebazaar.bytebazaar.service.definition.UtenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;



@Service
@RequiredArgsConstructor
public class UtenteServiceImpl implements UtenteService
{


    private final UtenteRepository utenteRepo;


    //funzionalità per il facade
    @Override
    public Utente getByUsername(String username) {
        return utenteRepo.findByUsername(username).orElseThrow(() -> new BadRequestException("utente non trovato"));
    }

    @Override
    public Utente getById(int id) {
        return utenteRepo.findById(id).orElseThrow(() -> new BadRequestException("utente non trovato"));
    }

    @Override
    public List<Utente> findAllByRuolo(Ruolo ruolo) {
        return utenteRepo.findAllByRuolo(ruolo);
    }

    @Override
    public void salva(Utente u) {
        utenteRepo.save(u);
    }





}
