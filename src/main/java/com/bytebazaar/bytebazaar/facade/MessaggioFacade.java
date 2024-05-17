package com.bytebazaar.bytebazaar.facade;

import com.bytebazaar.bytebazaar.dto.request.SendMessageRequestDTO;
import com.bytebazaar.bytebazaar.model.Messaggio;
import com.bytebazaar.bytebazaar.model.Utente;
import com.bytebazaar.bytebazaar.service.definition.MessaggioService;
import com.bytebazaar.bytebazaar.service.definition.UtenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MessaggioFacade
{
    private final MessaggioService serviceMessaggio;

    private final UtenteService serviceUtente;

    public boolean mandaMessaggio(Utente u, SendMessageRequestDTO request) {


        Utente ud = serviceUtente.getById(request.getIdUtente());


        if(u.getId() != request.getIdUtente())
        {
            Messaggio m2 = new Messaggio();
            m2.setUtente(u);
            m2.setIddestinatario(ud.getId());
            m2.setTesto(request.getTestoMessaggio());
            m2.setDataoraarrivo(LocalDateTime.now());

            serviceMessaggio.salva(m2);
        }

        return true;
    }

}
