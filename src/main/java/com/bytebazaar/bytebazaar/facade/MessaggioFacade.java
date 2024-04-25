package com.bytebazaar.bytebazaar.facade;

import com.bytebazaar.bytebazaar.dto.request.messaggio.SendMessageRequest;
import com.bytebazaar.bytebazaar.model.Messaggio;
import com.bytebazaar.bytebazaar.model.Utente;
import com.bytebazaar.bytebazaar.repository.ProdottoRepository;
import com.bytebazaar.bytebazaar.repository.UtenteRepository;
import com.bytebazaar.bytebazaar.service.definition.MessaggioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MessaggioFacade
{
    private final MessaggioService serviceMessaggio;

    @Autowired
    UtenteRepository utenteRepo;

    @Autowired
    ProdottoRepository prodottoRepo;


    public boolean mandaMessaggio(Utente u, SendMessageRequest request) {


        if(u.getIdutente() != request.getIdUtente())
        {
            Messaggio m2 = new Messaggio();
            m2.setUtente(u);
            m2.setIdmittente(u.getIdutente());
            m2.setIddestinatario(request.getIdUtente());
            m2.setTestoMessaggio(request.getTestoMessaggio());
            m2.setDataOraArrivo(LocalDateTime.now());

            serviceMessaggio.salva(m2);
        }

        return true;
    }

}
