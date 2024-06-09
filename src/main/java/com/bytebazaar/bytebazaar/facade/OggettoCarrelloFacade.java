package com.bytebazaar.bytebazaar.facade;

import com.bytebazaar.bytebazaar.dto.request.AddProductToCartRequestDTO;
import com.bytebazaar.bytebazaar.dto.request.DeleteObjectFromCartRequestDTO;
import com.bytebazaar.bytebazaar.dto.request.SubtractQuantityRequestDTO;
import com.bytebazaar.bytebazaar.exception.messaggiException.BadRequestException;
import com.bytebazaar.bytebazaar.exception.messaggiException.NotFoundException;
import com.bytebazaar.bytebazaar.exception.messaggiException.UnAuthorizedException;
import com.bytebazaar.bytebazaar.model.Carrello;
import com.bytebazaar.bytebazaar.model.Oggettocarrello;
import com.bytebazaar.bytebazaar.model.Prodotto;
import com.bytebazaar.bytebazaar.model.Utente;
import com.bytebazaar.bytebazaar.repository.CarrelloRepository;
import com.bytebazaar.bytebazaar.repository.ProdottoRepository;
import com.bytebazaar.bytebazaar.service.definition.OggettoCarrelloService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OggettoCarrelloFacade
{

    // Servizio per gestire le operazioni sugli oggetti nel carrello.
    private final OggettoCarrelloService serviceOggettoCarrello;

    // Repository per gestire le operazioni sul carrello.
    private final CarrelloRepository carrelloRepo;

    // Repository per gestire le operazioni sui prodotti.
    private final ProdottoRepository prodottoRepo;

    /**
     * Aggiunge un prodotto al carrello di un utente.
     * @param u L'utente a cui aggiungere il prodotto nel carrello.
     * @param request L'oggetto di richiesta contenente le informazioni del prodotto da aggiungere al carrello.
     * @return true se il prodotto è stato aggiunto con successo al carrello, altrimenti false.
     * @throws BadRequestException Se non è possibile aggiungere il prodotto al carrello.
     * @throws NotFoundException Se il prodotto non è trovato.
     */
    public boolean aggiungiAlCarrello(Utente u, AddProductToCartRequestDTO request) {
        // Trova il carrello con una data, o crea un nuovo carrello
        Carrello carrello = u.getCarrello().stream()
                .filter(c -> c.getDataacquisto() == null)
                .findFirst()
                .orElseGet(() -> {
                    Carrello newCarrello = new Carrello();
                    newCarrello.setUtente(u);
                    newCarrello.setDataacquisto(null);
                    return carrelloRepo.save(newCarrello);
                });

        // Ottiene il prodotto tramite l'ID fornito nella richiesta
        Optional<Prodotto> prodottoOptional = prodottoRepo.findById(request.getIdProdotto());

        // Verifica se il prodotto è presente nel repository
        if (prodottoOptional.isPresent()) {
            Prodotto p = prodottoOptional.get();

            // Ottiene l'oggetto carrello per il prodotto e il carrello specificati
            Optional<Oggettocarrello> og = serviceOggettoCarrello.getByCarrelloIdcarrelloAndProdottoIdProdotto(carrello.getId(), p.getId());

            // Verifica se l'utente sta cercando di aggiungere il proprio prodotto al carrello
            if (u.getId() == p.getUtente().getId()) {
                throw new BadRequestException("Non puoi comprare il tuo stesso prodotto");
            } else {
                if (og.isEmpty())
                {
                    Oggettocarrello newOggettoCarrello = new Oggettocarrello();
                    if (request.getIdProdotto() == p.getId()) {
                        newOggettoCarrello.setProdotto(p);
                    } else {
                        throw new BadRequestException("Prodotto inesistente");
                    }

                    if (request.getQuantita() > 0) {
                        newOggettoCarrello.setQuantita(request.getQuantita());
                    } else {
                        throw new BadRequestException("La quantità deve essere maggiore di 0");
                    }

                    // Imposta la relazione tra Carrello e Oggettocarrello
                    newOggettoCarrello.setCarrello(carrello);

                    // Salva l'Oggettocarrello e aggiorna il Carrello nel database utilizzando i repository
                    serviceOggettoCarrello.salva(newOggettoCarrello);
                    carrelloRepo.save(carrello);

                    return true; // Aggiunto con successo al carrello
                }
                else
                {
                    System.out.println("Sono dove vuoi tu");
                    // Se il prodotto è già presente nel carrello, aggiorna solo la quantità
                    Oggettocarrello o = og.get();
                    o.setQuantita(o.getQuantita() + request.getQuantita());
                    serviceOggettoCarrello.salva(o);
                    return true; // Quantità aggiornata con successo nel carrello
                }
            }
        } else {
            // Se il prodotto non è trovato, solleva un'eccezione NotFoundException
            throw new NotFoundException("Prodotto non trovato");
        }
    }


    /**
     * Modifica le quantità rimanenti dei prodotti nel carrello dopo un acquisto.
     *
     * @param u L'utente che effettua la modifica.
     * @param c Il carrello contenente gli oggetti da modificare.
     * @return True se l'operazione è stata completata con successo, altrimenti False.
     */
    public boolean modificaQuantitaRimanenti(Utente u, Carrello c) {
        List<Oggettocarrello> oggettiCarrelli = c.getOggettocarrello();

        // Cicla attraverso gli oggetti nel carrello
        for (Oggettocarrello og : oggettiCarrelli) {
            Prodotto p = og.getProdotto();
            // Verifica se la quantità disponibile del prodotto è sufficiente
            if (p.getQuantita() >= og.getQuantita()) {
                p.setQuantita(p.getQuantita() - og.getQuantita()); // Riduce la quantità disponibile
                prodottoRepo.save(p); // Salva le modifiche
                return true; // Restituisce true se l'operazione è avvenuta con successo
            } else {
                // Lancia un'eccezione BadRequest se la quantità non è sufficiente
                throw new BadRequestException("Impossibile completare l'operazione, verificare le quantità");
            }
        }

        return true; // Restituisce true se il carrello è vuoto o se tutte le operazioni sono state completate con successo
    }

    /**
     * Sottrae una quantità specifica da un oggetto nel carrello.
     *
     * @param request La richiesta di sottrazione di quantità.
     * @return True se l'operazione è stata completata con successo, altrimenti False.
     */
    public boolean sottraiQuantita(SubtractQuantityRequestDTO request) {
        Optional<Oggettocarrello> oggettocarrello = serviceOggettoCarrello.getById(request.getIdOggettocarrello());

        if (oggettocarrello.isPresent()) {
            Oggettocarrello og = oggettocarrello.get();
            // Verifica se il carrello ha già effettuato un acquisto
            if (og.getCarrello().getDataacquisto() == null) {
                og.setQuantita(request.getQuantita()); // Imposta la nuova quantità
                serviceOggettoCarrello.salva(og); // Salva le modifiche
                return true; // Restituisce true se l'operazione è stata completata con successo
            } else {
                // Lancia un'eccezione UnAuthorized se il carrello ha già effettuato un acquisto
                throw new UnAuthorizedException("Non autorizzato");
            }
        } else {
            // Lancia un'eccezione NotFoundException se l'oggetto carrello non è stato trovato
            throw new NotFoundException("Oggetto carrello non trovato");
        }
    }

    /**
     * Elimina un oggetto dal carrello.
     *
     * @param request La richiesta di eliminazione dell'oggetto dal carrello.
     * @return True se l'operazione è stata completata con successo, altrimenti False.
     */
    public boolean eliminaoggettocarrello(DeleteObjectFromCartRequestDTO request) {
        Optional<Oggettocarrello> oggettocarrello = serviceOggettoCarrello.getById(request.getIdOggettocarrello());

        if (oggettocarrello.isPresent()) {
            Oggettocarrello og = oggettocarrello.get();
            // Verifica se l'oggetto carrello esiste e se il carrello ha già effettuato un acquisto
            if (request.getIdOggettocarrello() == og.getId() && og.getCarrello().getDataacquisto() == null) {
                serviceOggettoCarrello.cancella(og); // Cancella l'oggetto carrello
                return true; // Restituisce true se l'operazione è stata completata con successo
            } else {
                // Lancia un'eccezione UnAuthorized se l'utente non è autorizzato o se l'oggetto carrello non è valido
                throw new UnAuthorizedException("Non autorizzato");
            }
        } else {
            // Lancia un'eccezione NotFoundException se l'oggetto carrello non è stato trovato
            throw new NotFoundException("Oggetto carrello non trovato");
        }
    }


}
