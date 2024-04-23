package com.bytebazaar.bytebazaar.service.implementation;

import com.bytebazaar.bytebazaar.dto.request.AcceptOrRejectRequest;
import com.bytebazaar.bytebazaar.exception.messaggiException.BadRequestException;
import com.bytebazaar.bytebazaar.exception.messaggiException.NotFoundException;
import com.bytebazaar.bytebazaar.exception.messaggiException.UnAuthorizedException;
import com.bytebazaar.bytebazaar.model.Richiesta;
import com.bytebazaar.bytebazaar.model.Ruolo;
import com.bytebazaar.bytebazaar.model.Stato;
import com.bytebazaar.bytebazaar.model.Utente;
import com.bytebazaar.bytebazaar.repository.RichiestaRepository;
import com.bytebazaar.bytebazaar.repository.UtenteRepository;
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
    public Richiesta findByUtenteUsername(String username) {
        return richiestaRepo.findByUtente_Username(username).orElseThrow(()-> new BadRequestException("Richiesta dell'utente non trovata"));
    }

    @Override
    public void salva(Richiesta r) {
        richiestaRepo.save(r);
    }

    @Override
    public Richiesta findByIdrichiesta(int idRichiesta)
    {
        return richiestaRepo.findByIdrichiesta(idRichiesta).orElseThrow(()-> new BadRequestException("Richiesta non trovata"));
    }
}
