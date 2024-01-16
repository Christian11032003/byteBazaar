package com.bytebazaar.bytebazaar.service.implementation;

import com.bytebazaar.bytebazaar.dto.request.MandaMessaggioRequest;
import com.bytebazaar.bytebazaar.exception.messaggiException.NotFoundException;
import com.bytebazaar.bytebazaar.model.Messaggio;
import com.bytebazaar.bytebazaar.model.Prodotto;
import com.bytebazaar.bytebazaar.model.Utente;
import com.bytebazaar.bytebazaar.repository.MessaggioRepository;
import com.bytebazaar.bytebazaar.repository.ProdottoRepository;
import com.bytebazaar.bytebazaar.repository.UtenteRepository;
import com.bytebazaar.bytebazaar.service.definition.MessaggioService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MessaggioServiceImpl implements MessaggioService
{

    @Autowired
    MessaggioRepository messaggioRepo;

    @Autowired
    UtenteRepository utenteRepo;

    @Autowired
    ProdottoRepository prodottoRepo;


    public boolean mandaMessaggio(Utente u, MandaMessaggioRequest request) {


        if(u.getIdutente() != request.getIdutente())
        {
            Messaggio m2 = new Messaggio();
            m2.setUtente(u);
            m2.setIdmittente(u.getIdutente());
            m2.setIddestinatario(request.getIdutente());
            m2.setTestoMessaggio(request.getTestoMessaggio());
            m2.setDataOraArrivo(LocalDateTime.now());

            messaggioRepo.save(m2);
        }

        return true;
    }

}