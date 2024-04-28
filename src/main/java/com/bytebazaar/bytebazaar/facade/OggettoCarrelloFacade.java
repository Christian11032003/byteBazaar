package com.bytebazaar.bytebazaar.facade;

import com.bytebazaar.bytebazaar.dto.request.oggettoCarello.AddProductToCartRequestDTO;
import com.bytebazaar.bytebazaar.dto.request.oggettoCarello.DeleteObjectFromCartRequestDTO;
import com.bytebazaar.bytebazaar.dto.request.prodotto.InsertOrModifyProductRequestDTO;
import com.bytebazaar.bytebazaar.dto.request.oggettoCarello.SubtractQuantityRequestDTO;
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

    private final OggettoCarrelloService serviceOggettoCarrello;

    private final CarrelloRepository carrelloRepo;

    private final ProdottoRepository prodottoRepo;

    public boolean aggiungiAlCarrello(Utente u, AddProductToCartRequestDTO request)
    {

        // Find the carrello with a date, or create a new one
        Carrello carrello = u.getCarrello().stream()
                .filter(c -> c.getDataacquisto() == null)
                .findFirst()
                .orElseGet(() -> {
                    Carrello newCarrello = new Carrello();
                    newCarrello.setUtente(u);
                    newCarrello.setDataacquisto(null);
                    return carrelloRepo.save(newCarrello);
                });

        Optional<Prodotto> prodottoOptional = prodottoRepo.findById(request.getIdProdotto());

        if(prodottoOptional.isPresent())
        {
            Prodotto p = prodottoOptional.get();
            Oggettocarrello og = serviceOggettoCarrello.getByCarrelloIdcarrelloAndProdottoIdProdotto(carrello.getId(),p.getId());

            if(u.getId() == p.getUtente().getId())
            {
                throw new BadRequestException("Non puoi comprare il tuo stesso prodotto");
            }
            else
            {
                if(og == null)
                {
                    Oggettocarrello o = new Oggettocarrello();
                    o.setProdotto(p);
                    o.setQuantita(request.getQuantita());


                    // Set the relationship between Carrello and Oggettocarrello
                    o.setCarrello(carrello);

                    // Save the Oggettocarrello and update the Carrello to the database using the repositories
                    serviceOggettoCarrello.salva(o);
                    carrelloRepo.save(carrello);

                    return true; // Successfully added to the carrello
                }

                else
                {


                    og.setQuantita(og.getQuantita() + request.getQuantita());
                    serviceOggettoCarrello.salva(og);
                    return true; //Successfully altered to the carrello

                }


            }



        }

        else
        {
            throw new NotFoundException("Prodotto non trovato");
        }

    }


    public boolean modificaQuantitaRimanenti(Utente u, Carrello c) {
        List<Oggettocarrello> oggettiCarrelli = c.getOggettocarrello();

        if (u == c.getOggettocarrello())
        {


            for (Oggettocarrello og : oggettiCarrelli) {

                Prodotto p = og.getProdotto();
                if (p.getQuantita() >= og.getQuantita())
                {
                    p.setQuantita(p.getQuantita() - og.getQuantita());
                    prodottoRepo.save(p);
                }

                else
                {
                    throw new BadRequestException("impossibile completare l'operazione verificare le quantità");
                }


            }

        }


        else
        {
            throw new UnAuthorizedException("Non autorizzato");
        }



        return true;

    }

    //rivedere sto metodo

    public boolean modificaOggettoCarrello(Utente u, InsertOrModifyProductRequestDTO request)
    {


        Optional<Prodotto> prodottoOptional = prodottoRepo.findByNome(request.getNome());

        if (prodottoOptional.isPresent())
        {

            Prodotto p = prodottoOptional.get();

            // Verifica che l'utente associato al prodotto sia lo stesso dell'utente autenticato
            if (p.getUtente().equals(u)) {
                // Modifica solo i campi non nulli nella richiesta
                if (request.getImmagine() != null) {
                    p.setImmagineprodotto(request.getImmagine());
                }
                if (request.getNome() != null) {
                    p.setNome(request.getNome());
                }
                if (request.getDescrizione() != null) {
                    p.setDescrizione(request.getDescrizione());
                }
                if (request.getPrezzo() > 0) {
                    p.setPrezzo(request.getPrezzo());
                }
                if (request.getQuantita() >= 0) {
                    p.setQuantita(request.getQuantita());
                }

                prodottoRepo.save(p);

                return true;
            }

            else
            {
                // L'utente non è autorizzato a modificare questo prodotto
                throw new UnAuthorizedException("Non Autorizzato");
            }
        }
        else
        {
            // Prodotto non trovato

            throw new NotFoundException("Prodotto non trovato");
        }


    }

    public boolean sottraiQuantita(SubtractQuantityRequestDTO request)
    {

        Oggettocarrello oggettocarrello = serviceOggettoCarrello.getById(request.getIdOggettocarrello());

        if(oggettocarrello != null)
        {

            if(oggettocarrello.getCarrello().getDataacquisto() == null)
            {
                oggettocarrello.setQuantita(request.getQuantita());
                serviceOggettoCarrello.salva(oggettocarrello);
                return true;
            }

            else
            {
                throw new UnAuthorizedException("Non autorizzato");
            }

        }

        else
        {
            throw new NotFoundException("Oggetto carrello non trovato");
        }



    }

    public boolean eliminaoggettocarrello(DeleteObjectFromCartRequestDTO request)
    {

        Oggettocarrello oggettocarrello = serviceOggettoCarrello.getById(request.getIdOggettocarrello());

        if (oggettocarrello != null)
        {



            if(request.getIdOggettocarrello() == oggettocarrello.getId() && oggettocarrello.getCarrello().getDataacquisto() == null)
            {
                serviceOggettoCarrello.cancella(oggettocarrello);
                return true;
            }

            else
            {
                throw new UnAuthorizedException("Non autorizzato");
            }

        }

        else
        {
            throw new NotFoundException("Oggetto carrello non trovato");
        }

    }


}
