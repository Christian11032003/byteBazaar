package com.bytebazaar.bytebazaar.facade;

import com.bytebazaar.bytebazaar.dto.request.messaggio.SendMessageRequestDTO;
import com.bytebazaar.bytebazaar.model.Messaggio;
import com.bytebazaar.bytebazaar.model.Utente;
import com.bytebazaar.bytebazaar.service.definition.MessaggioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MessaggioFacade
{
    private final MessaggioService serviceMessaggio;



    public boolean mandaMessaggio(Utente u, SendMessageRequestDTO request) {


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
