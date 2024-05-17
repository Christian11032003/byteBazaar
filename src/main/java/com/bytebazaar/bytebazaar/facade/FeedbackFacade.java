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


            boolean nonTrovaFeedback = feedbackList.stream().noneMatch(feedback -> feedback.getOggettocarrello().getCarrello().getUtente().getId() == (u.getId()));

            for (Carrello c : carrelloList) {
                for (Oggettocarrello o : c.getOggettocarrello()) {

                    if (p.getId() == o.getProdotto().getId() && c.getDataacquisto() != null && nonTrovaFeedback) {

                        Feedback newFeedback = new Feedback();
                        newFeedback.setOggettocarrello(o);
                        newFeedback.setDescrizione(request.getDescrizione());
                        newFeedback.setValutazione(request.getValutazione());
                        serviceFeedback.salva(newFeedback);
                        return true;

                    }


                    throw new BadRequestException("Impossibile aggiungere il feedback");


                }
            }

        }

        else
        {
            throw new NotFoundException("Prodotto non trovato");
        }

        throw new BadRequestException("Prodotto inesistente nel tuo vecchio carrello");

    }


    public boolean modificaFeedback(ModifyFeedbackRequestDTO request)
    {
        Feedback f = serviceFeedback.getByIdFeedback(request.getIdFeedback());
        Oggettocarrello o = f.getOggettocarrello();

        if(f != null)
        {
            f.setOggettocarrello(o);
            f.setDescrizione(request.getDescrizione());
            f.setValutazione(request.getValutazione());
            serviceFeedback.salva(f);
            return true;
        }

        else
        {
            throw new NotFoundException("Feedback non trovato");

        }


    }








}
