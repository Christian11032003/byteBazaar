package com.bytebazaar.bytebazaar.facade;

import com.bytebazaar.bytebazaar.dto.request.AddFeedbackRequestDTO;
import com.bytebazaar.bytebazaar.dto.request.ModifyFeedbackRequestDTO;
import com.bytebazaar.bytebazaar.exception.messaggiException.BadRequestException;
import com.bytebazaar.bytebazaar.exception.messaggiException.NotFoundException;
import com.bytebazaar.bytebazaar.model.*;
import com.bytebazaar.bytebazaar.repository.ProdottoRepository;
import com.bytebazaar.bytebazaar.service.definition.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedbackFacade
{
    // Servizio per gestire le operazioni legate ai feedback.
    private final FeedbackService serviceFeedback;

    // Repository per gestire le operazioni sui prodotti.
    private final ProdottoRepository prodottoRepo;

    /**
     * Aggiunge un feedback per un prodotto nel carrello di un utente.
     * @param u L'utente che aggiunge il feedback.
     * @param request L'oggetto di richiesta contenente le informazioni del feedback da aggiungere.
     * @return true se il feedback è stato aggiunto con successo, altrimenti false.
     * @throws BadRequestException Se non è possibile aggiungere il feedback.
     * @throws NotFoundException Se il prodotto non è trovato.
     */
    public boolean aggiungiFeedback(Utente u, AddFeedbackRequestDTO request) {
        // Ottiene il prodotto tramite l'ID fornito nella richiesta
        Optional<Prodotto> prodottoOptional = prodottoRepo.findById(request.getIdProdotto());

        // Verifica se il prodotto è presente nel repository
        if (prodottoOptional.isPresent()) {
            Prodotto p = prodottoOptional.get();

            // Ottiene la lista dei carrelli dell'utente
            List<Carrello> carrelloList = u.getCarrello();

            // Ottiene la lista dei feedback per il prodotto specificato
            List<Feedback> feedbackList = serviceFeedback.getByIdProdotto(request.getIdProdotto());

            // Verifica se l'utente ha già aggiunto un feedback per questo prodotto
            boolean nonTrovaFeedback = feedbackList.stream().noneMatch(feedback -> feedback.getOggettocarrello().getCarrello().getUtente().getId() == (u.getId()));

            // Controlla se il prodotto è stato acquistato e se l'utente non ha ancora aggiunto un feedback per esso
            for (Carrello c : carrelloList) {
                for (Oggettocarrello o : c.getOggettocarrello()) {
                    if (p.getId() == o.getProdotto().getId() && c.getDataacquisto() != null && nonTrovaFeedback) {
                        // Crea un nuovo feedback e lo aggiunge
                        Feedback newFeedback = new Feedback();
                        newFeedback.setOggettocarrello(o);
                        newFeedback.setDescrizione(request.getDescrizione());
                        newFeedback.setValutazione(request.getValutazione());
                        serviceFeedback.salva(newFeedback);
                        return true;
                    }
                }
            }
            // Se non è possibile aggiungere il feedback, solleva un'eccezione BadRequestException
            throw new BadRequestException("Impossibile aggiungere il feedback");
        } else {
            // Se il prodotto non è trovato, solleva un'eccezione NotFoundException
            throw new NotFoundException("Prodotto non trovato");
        }
    }

    /**
     * Modifica un feedback esistente.
     * @param request L'oggetto di richiesta contenente le informazioni del feedback da modificare.
     * @return true se il feedback è stato modificato con successo, altrimenti false.
     * @throws NotFoundException Se il feedback non è trovato.
     */
    public boolean modificaFeedback(ModifyFeedbackRequestDTO request) {
        // Ottiene il feedback tramite l'ID fornito nella richiesta
        Feedback f = serviceFeedback.getByIdFeedback(request.getIdFeedback());

        // Verifica se il feedback è stato trovato
        if (f != null) {
            // Ottiene l'oggetto carrello associato al feedback
            Oggettocarrello o = f.getOggettocarrello();

            // Modifica le informazioni del feedback e lo salva
            f.setOggettocarrello(o);
            f.setDescrizione(request.getDescrizione());
            f.setValutazione(request.getValutazione());
            serviceFeedback.salva(f);
            return true;
        } else {
            // Se il feedback non è trovato, solleva un'eccezione NotFoundException
            throw new NotFoundException("Feedback non trovato");
        }

    }



}
