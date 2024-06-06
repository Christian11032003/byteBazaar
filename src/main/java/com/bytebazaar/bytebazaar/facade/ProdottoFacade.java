package com.bytebazaar.bytebazaar.facade;

import com.bytebazaar.bytebazaar.dto.request.InsertOrModifyProductRequestDTO;
import com.bytebazaar.bytebazaar.exception.messaggiException.BadRequestException;
import com.bytebazaar.bytebazaar.exception.messaggiException.NotFoundException;
import com.bytebazaar.bytebazaar.exception.messaggiException.SwitchingProtocolException;
import com.bytebazaar.bytebazaar.exception.messaggiException.UnAuthorizedException;
import com.bytebazaar.bytebazaar.model.*;
import com.bytebazaar.bytebazaar.repository.RichiestaRepository;
import com.bytebazaar.bytebazaar.service.definition.ProdottoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdottoFacade
{
    // Servizio per gestire le operazioni sui prodotti.
    private final ProdottoService serviceProdotto;

    // Repository per gestire le operazioni sulle richieste.
    private final RichiestaRepository richiestaRepo;



    /**
     * Registra un nuovo prodotto per l'utente specificato.
     *
     * @param u L'utente che sta registrando il prodotto.
     * @param request I dettagli del prodotto da registrare.
     * @return True se l'operazione è stata completata con successo, altrimenti False.
     */
    public boolean registraProdotto(Utente u, InsertOrModifyProductRequestDTO request) {
        // Ottiene la richiesta dell'utente
        Optional<Richiesta> richiestaOptional = richiestaRepo.findByUtente_Username(u.getUsername());

        if (richiestaOptional.isPresent()) {
            Richiesta r = richiestaOptional.get();
            List<Prodotto> prodottoList = u.getProdotto();

            // Controlla se il prodotto già esiste per l'utente
            boolean existProduct = prodottoList.stream().anyMatch(prodotto -> prodotto.getNome().equalsIgnoreCase(request.getNome()));

            // Verifica se la richiesta dell'utente è stata accettata e se il prodotto non esiste già
            if (r.getStato() == Stato.ACCETTATO && !existProduct) {
                Prodotto p = new Prodotto();
                p.setUtente(u);

                // Imposta i dettagli del prodotto dalla richiesta
                if (request.getNome() != null) {
                    p.setNome(request.getNome());
                } else {
                    throw new BadRequestException("Nome non può essere uguale null");
                }

                if (request.getDescrizione() != null) {
                    p.setDescrizione(request.getDescrizione());
                } else {
                    throw new BadRequestException("Descrizione non può essere uguale da null");
                }

                if (request.getPrezzo() > 0) {
                    p.setPrezzo(request.getPrezzo());
                } else {
                    throw new BadRequestException("Prezzo non può essere minore di 0");
                }

                if (request.getQuantita() > 0) {
                    p.setQuantita(request.getQuantita());
                } else {
                    throw new BadRequestException("Quantità non può essere minore di 0");
                }

                if (request.getCondizione() != null) {
                    p.setCondizione(request.getCondizione());
                } else {
                    throw new BadRequestException("la condizione non può essere null");
                }

                serviceProdotto.salva(p);
                return true; // Restituisce true se l'operazione è stata completata con successo
            } else if (r.getStato() == Stato.ACCETTATO) {
                // Se la richiesta è stata accettata ma il prodotto esiste già, lancia un'eccezione SwitchingProtocol
                throw new SwitchingProtocolException("Attenzione! Non puoi modificare qui il prodotto, vai nel header modificaProdotto");
            } else {
                // Se la richiesta non è stata accettata, lancia un'eccezione UnAuthorized
                throw new UnAuthorizedException("Impossibile effetuare l'operazione, controllare la richiesta se è stata accettata ");
            }
        } else {
            // Se l'utente non è stato trovato, lancia un'eccezione NotFoundException
            throw new NotFoundException("Utente non trovato");
        }
    }

    /**
     * Modifica un prodotto esistente per l'utente specificato.
     *
     * @param u L'utente che sta modificando il prodotto.
     * @param request I dettagli del prodotto da modificare.
     * @return True se l'operazione è stata completata con successo, altrimenti False.
     */
    public boolean modificaProdotto(Utente u, InsertOrModifyProductRequestDTO request) {
        // Ottiene il prodotto dal servizio Prodotto
        Prodotto p = serviceProdotto.getByNome(request.getNome());

        if (p != null) {
            // Verifica che l'utente associato al prodotto sia lo stesso dell'utente autenticato
            if (p.getUtente().getId() == u.getId()) {
                // Modifica solo i campi non nulli nella richiesta

                if (request.getNome() != null) {
                    p.setNome(request.getNome());
                } else {
                    p.setNome(p.getNome());
                }
                if (request.getDescrizione() != null) {
                    p.setDescrizione(request.getDescrizione());
                } else {
                    p.setDescrizione(p.getDescrizione());
                }
                if (request.getPrezzo() > 0) {
                    p.setPrezzo(request.getPrezzo());
                } else {
                    throw new BadRequestException("Prezzo non può essere minore di 0");
                }
                if(request.getCondizione() != null)
                {
                    p.setCondizione(request.getCondizione());
                }
                else
                {
                    p.setCondizione(p.getCondizione());
                }

                if (request.getQuantita() > 0) {
                    p.setQuantita(request.getQuantita());
                } else {
                    throw new BadRequestException("Quantita non può essere minore di 0");
                }

                serviceProdotto.salva(p); // Salva le modifiche
                return true; // Restituisce true se l'operazione è stata completata con successo
            } else {
                // L'utente non è autorizzato a modificare questo prodotto
                throw new UnAuthorizedException("Non Autorizzato");
            }
        } else {
            // Lancia un'eccezione BadRequest se il prodotto non è presente in vendita
            throw new BadRequestException("Prodotto non presente in vendita");
        }
    }

}


