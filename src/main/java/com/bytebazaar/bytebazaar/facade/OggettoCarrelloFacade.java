package com.bytebazaar.bytebazaar.facade;

import com.bytebazaar.bytebazaar.dto.request.AddProductToCartRequest;
import com.bytebazaar.bytebazaar.dto.request.DeleteObjectFromCartRequest;
import com.bytebazaar.bytebazaar.dto.request.InsertOrModifyProductRequest;
import com.bytebazaar.bytebazaar.dto.request.SubtractQuantityRequest;
import com.bytebazaar.bytebazaar.exception.messaggiException.BadRequestException;
import com.bytebazaar.bytebazaar.exception.messaggiException.NotFoundException;
import com.bytebazaar.bytebazaar.exception.messaggiException.UnAuthorizedException;
import com.bytebazaar.bytebazaar.model.Carrello;
import com.bytebazaar.bytebazaar.model.OggettoCarrello;
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

    public boolean aggiungiAlCarrello(Utente u, AddProductToCartRequest request)
    {

        // Find the carrello with a date, or create a new one
        Carrello carrello = u.getCarrello().stream()
                .filter(c -> c.getDataAcquisto() == null)
                .findFirst()
                .orElseGet(() -> {
                    Carrello newCarrello = new Carrello();
                    newCarrello.setUtente(u);
                    newCarrello.setDataAcquisto(null);
                    return carrelloRepo.save(newCarrello);
                });

        Optional<Prodotto> prodottoOptional = prodottoRepo.findByIdProdotto(request.getIdProdotto());

        if(prodottoOptional.isPresent())
        {
            Prodotto p = prodottoOptional.get();
            OggettoCarrello og = serviceOggettoCarrello.getByCarrelloIdcarrelloAndProdottoIdProdotto(carrello.getIdcarrello(),p.getIdProdotto());

            if(u.getIdutente() == p.getUtente().getIdutente())
            {
                throw new BadRequestException("Non puoi comprare il tuo stesso prodotto");
            }
            else
            {
                if(og == null)
                {
                    OggettoCarrello o = new OggettoCarrello();
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


    public boolean modificaQuantitaRimanenti(Utente u, Carrello c)
    {
        List<OggettoCarrello> oggettiCarrelli = c.getOggettoCarrello();

        for(OggettoCarrello og : oggettiCarrelli)
        {

            Prodotto p = og.getProdotto();
            if(p.getQuantita() >= og.getQuantita())
            {
                p.setQuantita(p.getQuantita()-og.getQuantita());
                prodottoRepo.save(p);
            }

            else
            {
                throw new BadRequestException("impossibile completare l'operazione verificare le quantità");
            }


        }

        return true;

    }

    //rivedere sto metodo

    public boolean modificaOggettoCarrello(Utente u, InsertOrModifyProductRequest request)
    {


        Optional<Prodotto> prodottoOptional = prodottoRepo.findByNome(request.getNome());

        if (prodottoOptional.isPresent())
        {

            Prodotto p = prodottoOptional.get();

            // Verifica che l'utente associato al prodotto sia lo stesso dell'utente autenticato
            if (p.getUtente().equals(u)) {
                // Modifica solo i campi non nulli nella richiesta
                if (request.getImmagine() != null) {
                    p.setImmagineProdotto(request.getImmagine());
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

    public boolean sottraiQuantita(Utente u, SubtractQuantityRequest request)
    {

        OggettoCarrello oggettocarrello = serviceOggettoCarrello.getById(request.getIdoggettocarrello());

        if(oggettocarrello != null)
        {

            if(oggettocarrello.getCarrello().getDataAcquisto() == null)
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

    public boolean eliminaoggettocarrello(Utente u, DeleteObjectFromCartRequest request)
    {

        OggettoCarrello oggettocarrello = serviceOggettoCarrello.getById(request.getIdoggettocarrello());

        if (oggettocarrello != null)
        {



            if(request.getIdoggettocarrello() == oggettocarrello.getIdoggettocarrello() && oggettocarrello.getCarrello().getDataAcquisto() == null)
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
