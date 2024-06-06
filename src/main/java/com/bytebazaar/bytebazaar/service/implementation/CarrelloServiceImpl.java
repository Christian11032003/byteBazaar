package com.bytebazaar.bytebazaar.service.implementation;


import com.bytebazaar.bytebazaar.model.Carrello;
import com.bytebazaar.bytebazaar.repository.CarrelloRepository;
import com.bytebazaar.bytebazaar.service.definition.CarrelloService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
// Implementazione del servizio per la gestione del carrello degli acquisti
public class CarrelloServiceImpl implements CarrelloService {

    // Il CarrelloRepository è una dipendenza di questo servizio, utilizzato per le operazioni di accesso ai dati relative alle entità 'carrello'.
    private final CarrelloRepository carrelloRepo;

    /**
     * Metodo per ottenere il carrello di un utente tramite il suo nome utente.
     *
     * @param username il nome utente dell'utente associato al carrello
     * @return un Optional contenente il carrello se trovato, altrimenti un Optional vuoto
     */
    @Override
    public Optional<Carrello> getByUsername(String username) {
        return carrelloRepo.findCarrelloByUtente_UsernameAndDataacquistoIsNull(username);
    }

    /**
     * Metodo per salvare un carrello nel database.
     *
     * @param c il carrello da salvare
     */
    @Override
    public void salva(Carrello c) {
        carrelloRepo.save(c);
    }
}
