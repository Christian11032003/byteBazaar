package com.bytebazaar.bytebazaar.service.implementation;

import com.bytebazaar.bytebazaar.dto.request.BannedOrUnBannedRequest;
import com.bytebazaar.bytebazaar.dto.request.LoginRequest;
import com.bytebazaar.bytebazaar.dto.request.RegistrationUserRequest;
import com.bytebazaar.bytebazaar.exception.messaggiException.BadRequestException;
import com.bytebazaar.bytebazaar.exception.messaggiException.NotFoundException;
import com.bytebazaar.bytebazaar.exception.messaggiException.UnAuthorizedException;
import com.bytebazaar.bytebazaar.model.Prodotto;
import com.bytebazaar.bytebazaar.model.Ruolo;
import com.bytebazaar.bytebazaar.model.Utente;
import com.bytebazaar.bytebazaar.repository.ProdottoRepository;
import com.bytebazaar.bytebazaar.repository.UtenteRepository;
import com.bytebazaar.bytebazaar.security.TokenUtil;
import com.bytebazaar.bytebazaar.service.definition.RichiestaService;
import com.bytebazaar.bytebazaar.service.definition.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;



@Service
public class UtenteServiceImpl implements UtenteService
{

    @Autowired
    private UtenteRepository utenteRepo;


    //funzionalità per il facade
    @Override
    public Utente getByUsername(String username) {
        return utenteRepo.findByUsername(username).orElseThrow(() -> new BadRequestException("utente non trovato"));
    }

    @Override
    public Utente getById(int id) {
        return utenteRepo.findByIdutente(id).orElseThrow(() -> new BadRequestException("utente non trovato"));
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
