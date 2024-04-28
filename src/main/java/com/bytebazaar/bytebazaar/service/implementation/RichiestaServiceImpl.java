package com.bytebazaar.bytebazaar.service.implementation;

import com.bytebazaar.bytebazaar.exception.messaggiException.BadRequestException;
import com.bytebazaar.bytebazaar.model.Richiesta;
import com.bytebazaar.bytebazaar.repository.RichiestaRepository;
import com.bytebazaar.bytebazaar.service.definition.RichiestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RichiestaServiceImpl implements RichiestaService
{

    @Autowired
    private RichiestaRepository richiestaRepo;


    @Override
    public Optional<Richiesta> findByUtenteUsername(String username) {
        return richiestaRepo.findByUtente_Username(username);
    }

    @Override
    public void salva(Richiesta r) {
        richiestaRepo.save(r);
    }

    @Override
    public Richiesta findByIdrichiesta(int idRichiesta)
    {
        return richiestaRepo.findById(idRichiesta).orElseThrow(()-> new BadRequestException("Richiesta non trovata"));
    }
}
