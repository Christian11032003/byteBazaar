package com.bytebazaar.bytebazaar.facade;

import com.bytebazaar.bytebazaar.exception.messaggiException.BadRequestException;
import com.bytebazaar.bytebazaar.model.Carrello;
import com.bytebazaar.bytebazaar.model.Utente;
import com.bytebazaar.bytebazaar.service.definition.CarrelloService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarrelloFacade {

    // Servizio per gestire le operazioni legate al carrello degli acquisti.
    private final CarrelloService carrelloService;

    // Fornisce un'interfaccia per gestire le operazioni sugli oggetti presenti nel carrello.
    private final OggettoCarrelloFacade oggettoCarrelloFacade;


    /**
     * Conferma l'acquisto del carrello per l'utente specificato.
     * Se il carrello esiste e non è stato ancora acquistato, lo contrassegna come acquistato e aggiorna la data di acquisto.
     * Modifica anche le quantità rimanenti degli oggetti nel carrello.
     * @param u L'utente per cui confermare l'acquisto del carrello.
     * @return true se l'acquisto è stato confermato con successo, altrimenti false.
     * @throws BadRequestException Se il carrello non è stato trovato o se è già stato acquistato.
     */
    public boolean confermaCarrello(Utente u) {

        // Ottiene il carrello dell'utente
        Optional<Carrello> carrello = carrelloService.getByUsername(u.getUsername());

        // Verifica se il carrello esiste
        if (carrello.isPresent()) {

            // Ottiene l'istanza del carrello
            Carrello c = carrello.get();

            // Controlla se il carrello è già stato acquistato
            if (c.getDataacquisto() != null) {
                throw new BadRequestException("Carrello non acquistato");
            }

            // Imposta la data di acquisto del carrello all'istante attuale
            c.setDataacquisto(LocalDateTime.now());

            // Modifica le quantità rimanenti degli oggetti nel carrello e verifica se è stato possibile modificarle
            if (oggettoCarrelloFacade.modificaQuantitaRimanenti(u, c)) {

                // Salva le modifiche al carrello
                carrelloService.salva(c);
            }

            return true; // Ritorna true per indicare che l'acquisto è stato confermato con successo

        }

        // Se il carrello non è stato trovato, solleva un'eccezione BadRequestException
        throw new BadRequestException("Carrello non trovato");
    }
}
