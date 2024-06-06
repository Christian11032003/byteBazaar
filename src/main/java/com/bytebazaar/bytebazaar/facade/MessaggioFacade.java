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
    // Servizio per gestire le operazioni legate ai messaggi.
    private final MessaggioService serviceMessaggio;

    // Servizio per gestire le operazioni sugli utenti.
    private final UtenteService serviceUtente;

    /**
     * Invia un messaggio da un utente a un altro.
     * @param u L'utente che invia il messaggio.
     * @param request L'oggetto di richiesta contenente le informazioni del messaggio da inviare.
     * @return true se il messaggio è stato inviato con successo, altrimenti false.
     */
    public boolean mandaMessaggio(Utente u, SendMessageRequestDTO request) {
        // Ottiene l'utente destinatario del messaggio tramite l'ID fornito nella richiesta
        Utente ud = serviceUtente.getById(request.getIdUtente());

        // Verifica se l'utente che invia il messaggio è diverso dall'utente destinatario
        if (u.getId() != request.getIdUtente()) {
            // Crea un nuovo messaggio e lo invia
            Messaggio m2 = new Messaggio();
            m2.setUtente(u);
            m2.setIddestinatario(ud.getId());
            m2.setTesto(request.getTestoMessaggio());
            m2.setDataoraarrivo(LocalDateTime.now());
            serviceMessaggio.salva(m2);
        }
        return true; // Ritorna true per indicare che il messaggio è stato inviato con successo
    }

}
