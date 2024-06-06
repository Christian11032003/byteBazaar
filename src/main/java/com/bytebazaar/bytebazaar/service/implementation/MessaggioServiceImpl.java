package com.bytebazaar.bytebazaar.service.implementation;

import com.bytebazaar.bytebazaar.model.Messaggio;
import com.bytebazaar.bytebazaar.repository.MessaggioRepository;
import com.bytebazaar.bytebazaar.service.definition.MessaggioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// La classe implementa l'interfaccia MessaggioService, fornendo l'implementazione dei metodi definiti nell'interfaccia.

@Service
@RequiredArgsConstructor
public class MessaggioServiceImpl implements MessaggioService {

    // Il MessaggioRepository è una dipendenza di questo servizio, utilizzata per le operazioni di accesso ai dati relative alle entità 'Messaggio'.
    private final MessaggioRepository messaggioRepo;

    /**
     * Salva un'istanza di Messaggio nel repository.
     *
     * @param m l'istanza di Messaggio da salvare
     */
    @Override
    public void salva(Messaggio m) {
        messaggioRepo.save(m);
    }
}