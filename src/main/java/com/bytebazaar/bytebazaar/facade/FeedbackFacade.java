package com.bytebazaar.bytebazaar.facade;

import com.bytebazaar.bytebazaar.dto.request.AddFeedbackRequest;
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


    public boolean aggiungiFeedback(Utente u, AddFeedbackRequest request)
    {


        Optional<Prodotto> prodottoOptional = prodottoRepo.findByIdProdotto(request.getIdprodotto());

        if (prodottoOptional.isPresent())
        {

            Prodotto p = prodottoOptional.get();

            List<Carrello> carrelloList = u.getCarrello();

            List<Feedback> feedbackList = serviceFeedback.getByIdProdotto(request.getIdprodotto());


            boolean trovaFeedback = feedbackList.stream().noneMatch(feedback -> feedback.getOggettocarrello().getCarrello().getUtente().getIdutente() == (u.getIdutente()));

            for (Carrello c : carrelloList) {
                for (OggettoCarrello o : c.getOggettoCarrello()) {

                    if (p.getIdProdotto() == o.getProdotto().getIdProdotto() && c.getDataAcquisto() != null && trovaFeedback) {

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
            throw new NotFoundException("Prodotto non trovato");
        }

        throw new BadRequestException("Errore");

    }





}
