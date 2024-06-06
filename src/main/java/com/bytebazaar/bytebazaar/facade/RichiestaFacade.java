package com.bytebazaar.bytebazaar.facade;

import com.bytebazaar.bytebazaar.dto.request.AcceptOrRejectRequestDTO;
import com.bytebazaar.bytebazaar.exception.messaggiException.NotFoundException;
import com.bytebazaar.bytebazaar.exception.messaggiException.UnAuthorizedException;
import com.bytebazaar.bytebazaar.model.Richiesta;
import com.bytebazaar.bytebazaar.model.Ruolo;
import com.bytebazaar.bytebazaar.model.Stato;
import com.bytebazaar.bytebazaar.model.Utente;
import com.bytebazaar.bytebazaar.repository.UtenteRepository;
import com.bytebazaar.bytebazaar.service.definition.RichiestaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RichiestaFacade
{

    // Servizio per gestire le operazioni sulle richieste.
    private final RichiestaService serviceRichiesta;

    // Repository per gestire le operazioni sugli utenti.
    private final UtenteRepository utenteRepo;

    /**
     * Registra una nuova richiesta per l'utente specificato.
     *
     * @param u L'utente per il quale registrare la richiesta.
     * @return True se l'operazione è stata completata con successo, altrimenti False.
     */
    public boolean registrazioneRichiesta(Utente u) {
        // Crea una nuova richiesta e la salva con lo stato "RICHIESTA"
        Richiesta r = new Richiesta();
        r.setUtente(u);
        r.setStato(Stato.RICHIESTA);
        serviceRichiesta.salva(r); // Salva la richiesta
        return true; // Restituisce true se l'operazione è stata completata con successo
    }

    /**
     * Gestisce la richiesta per l'utente specificato.
     *
     * @param u L'utente per il quale gestire la richiesta.
     * @return True se l'operazione è stata completata con successo, altrimenti False.
     * @throws UnAuthorizedException Se la richiesta è già stata somministrata.
     */
    public boolean richiesta(Utente u) {
        // Controlla se l'utente ha già inviato una richiesta
        Optional<Richiesta> r = serviceRichiesta.findByUtenteUsername(u.getUsername());

        if (r.isEmpty()) {
            // Se l'utente non ha ancora inviato una richiesta, crea una nuova richiesta con stato "RICHIESTA" e la salva
            Richiesta newRichiesta = new Richiesta();
            newRichiesta.setUtente(u);
            newRichiesta.setStato(Stato.RICHIESTA);
            serviceRichiesta.salva(newRichiesta); // Salva la nuova richiesta
            return true; // Restituisce true se l'operazione è stata completata con successo
        } else {
            // Se l'utente ha già inviato una richiesta, lancia un'eccezione UnAuthorized
            throw new UnAuthorizedException("Richiesta già somministrata, attendere l'approvazione di un admin");
        }
    }

    /**
     * Modifica lo stato di una richiesta in base alla decisione dell'amministratore.
     *
     * @param request I dettagli della richiesta da modificare.
     * @return True se l'operazione è stata completata con successo, altrimenti False.
     * @throws NotFoundException Se si verifica un errore nella modifica dello stato della richiesta.
     */
    public boolean modifyTheRequest(AcceptOrRejectRequestDTO request) {
        // Ottiene la richiesta dal servizio Richiesta
        Richiesta r = serviceRichiesta.findByIdrichiesta(request.getIdRichiesta());
        Utente ur = r.getUtente();

        // Modifica lo stato della richiesta in base alla decisione dell'amministratore
        if (request.getStato().equals(Stato.ACCETTATO)) {
            if (ur.getRuolo() == Ruolo.VENDITORE) {
                r.setStato(Stato.ACCETTATO);
            } else if (ur.getRuolo() == Ruolo.CLIENTE) {
                ur.setRuolo(Ruolo.VENDITORE);
                r.setStato(Stato.ACCETTATO);
            }
        } else if (request.getStato().equals(Stato.RIFIUTATO)) {
            if (ur.getRuolo() == Ruolo.VENDITORE) {
                ur.setRuolo(Ruolo.CLIENTE);
                r.setStato(Stato.RIFIUTATO);
            } else if (ur.getRuolo() == Ruolo.CLIENTE) {
                r.setStato(Stato.RIFIUTATO);
            }
        } else {
            // Se lo stato della richiesta non è valido, lancia un'eccezione NotFoundException
            throw new NotFoundException("Errore");
        }

        // Aggiorna l'utente e la richiesta nel repository
        utenteRepo.save(ur);
        serviceRichiesta.salva(r);

        return true; // Restituisce true se l'operazione è stata completata con successo
    }
}



