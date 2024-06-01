package com.bytebazaar.bytebazaar.facade;

import com.bytebazaar.bytebazaar.dto.request.AddProductToCartRequestDTO;
import com.bytebazaar.bytebazaar.dto.request.DeleteObjectFromCartRequestDTO;
import com.bytebazaar.bytebazaar.dto.request.InsertOrModifyProductRequestDTO;
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
            Optional<Oggettocarrello> og = serviceOggettoCarrello.getByCarrelloIdcarrelloAndProdottoIdProdotto(carrello.getId(),p.getId());

            if(u.getId() == p.getUtente().getId())
            {
                throw new BadRequestException("Non puoi comprare il tuo stesso prodotto");
            }
            else
            {
                if(og.isEmpty())
                {
                    Oggettocarrello newOggettoCarrello = new Oggettocarrello();
                    if(request.getIdProdotto() == p.getId())
                    {
                        newOggettoCarrello.setProdotto(p);
                    }

                    else
                    {
                        throw new BadRequestException("prodotto inesistente");
                    }

                    if(request.getQuantita() > 0)
                    {
                        newOggettoCarrello.setQuantita(request.getQuantita());
                    }

                    else
                    {
                        throw new BadRequestException("la quantità deve essere maggiore di 0");
                    }




                    // Set the relationship between Carrello and Oggettocarrello
                    newOggettoCarrello.setCarrello(carrello);

                    // Save the Oggettocarrello and update the Carrello to the database using the repositories
                    serviceOggettoCarrello.salva(newOggettoCarrello);
                    carrelloRepo.save(carrello);

                    return true; // Successfully added to the carrello
                }

                else
                {

                    Oggettocarrello o = og.get();
                    o.setQuantita(o.getQuantita() + request.getQuantita());
                    serviceOggettoCarrello.salva(o);
                    return true; //Successfully altered to the carrello

                }


            }



        }

        else
        {
            throw new NotFoundException("Prodotto non trovato");
        }

    }


    public boolean modificaQuantitaRimanenti(Utente u, Carrello c)
    {
        List<Oggettocarrello> oggettiCarrelli = c.getOggettocarrello();

        //TO DO da rivedere
        //if (c.getUtente().getCarrello() == u.getCarrello())


        for (Oggettocarrello og : oggettiCarrelli) {

            Prodotto p = og.getProdotto();
            if (p.getQuantita() >= og.getQuantita())
            {
                p.setQuantita(p.getQuantita() - og.getQuantita());
                prodottoRepo.save(p);
                return true;

            }


            else
            {
                throw new BadRequestException("impossibile completare l'operazione verificare le quantità");
            }


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

        Optional<Oggettocarrello> oggettocarrello = serviceOggettoCarrello.getById(request.getIdOggettocarrello());

        if(oggettocarrello.isPresent())
        {

            Oggettocarrello og = oggettocarrello.get();

            if(og.getCarrello().getDataacquisto() == null)
            {
                og.setQuantita(request.getQuantita());
                serviceOggettoCarrello.salva(og);
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

        Optional<Oggettocarrello> oggettocarrello = serviceOggettoCarrello.getById(request.getIdOggettocarrello());

        if (oggettocarrello.isPresent())
        {

            Oggettocarrello og = oggettocarrello.get();

            if(request.getIdOggettocarrello() == og.getId() && og.getCarrello().getDataacquisto() == null)
            {
                serviceOggettoCarrello.cancella(og);
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
