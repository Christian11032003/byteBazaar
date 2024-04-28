package com.bytebazaar.bytebazaar.facade;

import com.bytebazaar.bytebazaar.dto.request.feedback.AddFeedbackRequestDTO;
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
    private final FeedbackService serviceFeedback;

    private final ProdottoRepository prodottoRepo;


    public boolean aggiungiFeedback(Utente u, AddFeedbackRequestDTO request)
    {


        Optional<Prodotto> prodottoOptional = prodottoRepo.findById(request.getIdProdotto());

        if (prodottoOptional.isPresent())
        {

            Prodotto p = prodottoOptional.get();

            List<Carrello> carrelloList = u.getCarrello();

            List<Feedback> feedbackList = serviceFeedback.getByIdProdotto(request.getIdProdotto());


            boolean trovaFeedback = feedbackList.stream().noneMatch(feedback -> feedback.getOggettocarrello().getCarrello().getUtente().getId() == (u.getId()));

            for (Carrello c : carrelloList) {
                for (Oggettocarrello o : c.getOggettocarrello()) {

                    if (p.getId() == o.getProdotto().getId() && c.getDataacquisto() != null && trovaFeedback) {

                        Feedback f = new Feedback();
                        f.setOggettocarrello(o);
                        f.setDescrizione(request.getDescrizione());
                        f.setValutazione(request.getValutazione());
                        serviceFeedback.salva(f);
                        return true;

                    } else {
                        throw new BadRequestException("Errore");
                    }

                }
            }

        }

        else
        {
            System.out.println("aaaaaaaa");
            throw new NotFoundException("Prodotto non trovato");
        }

        throw new BadRequestException("Errore");

    }





}
