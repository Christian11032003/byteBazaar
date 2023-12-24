package com.bytebazaar.bytebazaar.service.implementation;

import com.bytebazaar.bytebazaar.dto.request.*;
import com.bytebazaar.bytebazaar.exception.messaggiException.BadRequestException;
import com.bytebazaar.bytebazaar.exception.messaggiException.NotFoundException;
import com.bytebazaar.bytebazaar.exception.messaggiException.UnAuthorizedException;
import com.bytebazaar.bytebazaar.model.*;
import com.bytebazaar.bytebazaar.repository.*;
import com.bytebazaar.bytebazaar.service.definition.CarrelloService;
import com.bytebazaar.bytebazaar.service.definition.OggettocarrelloService;
import com.bytebazaar.bytebazaar.utils.Util;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OggettocarrelloServiceImpl implements OggettocarrelloService
{
    @Autowired
    private OggettocarrelloRepository oggettocarrelloRepo;

    @Autowired
    private UtenteRepository utenteRepo;

    @Autowired
    private CarrelloRepository carrelloRepo;

    @Autowired
    private ProdottoRepository prodottoRepo;
    @SneakyThrows
    public boolean aggiungiAlCarrello(AggiungiProdottoInCarrelloRequest request)
    {
        Optional<Utente> utenteOptional = utenteRepo.findByUsernameAndPassword(request.getUsername(), request.getPassword());

        if (utenteOptional.isPresent() &&
                ((Util.roleControl(request.getUsername(), request.getPassword(), Ruolo.CLIENTEVENDITORE)) ||
                        (Util.roleControl(request.getUsername(), request.getPassword(), Ruolo.CLIENTE)))) {

            Utente u = utenteOptional.get();

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

            Optional<Prodotto> prodottoOptional = prodottoRepo.findByNome(request.getNomeProdotto());

            if(prodottoOptional.isPresent())
            {
                Prodotto p = prodottoOptional.get();
                Optional<Oggettocarrello> oggettocarrello = oggettocarrelloRepo.findByCarrello_IdcarrelloAndProdotto_IdProdotto(carrello.getIdcarrello(),p.getIdProdotto());

                if(u.getIdutente() == p.getUtente().getIdutente())
                {
                    throw new BadRequestException("Non puoi comprare il tuo stesso prodotto");
                }
                else
                {
                    if(oggettocarrello.isEmpty())
                    {
                        Oggettocarrello o = new Oggettocarrello();
                        o.setProdotto(p);
                        o.setQuantita(request.getQuantita());


                        // Set the relationship between Carrello and Oggettocarrello
                        o.setCarrello(carrello);

                        // Save the Oggettocarrello and update the Carrello to the database using the repositories
                        oggettocarrelloRepo.save(o);
                        carrelloRepo.save(carrello);

                        return true; // Successfully added to the carrello
                    }

                    else
                    {

                        Oggettocarrello o = oggettocarrello.get();
                        o.setQuantita(o.getQuantita() + request.getQuantita());
                        oggettocarrelloRepo.save(o);
                        return true; //Successfully altered to the carrello

                    }



                }



            }

            else
            {
                throw new NotFoundException("Prodotto non trovato");
            }



        }

        else
        {
            throw new UnAuthorizedException("Non autorizzato");
        }

    }

    @SneakyThrows
    public boolean modificaQuantitaRimanenti(LoginRequest request, Carrello c)
    {
        List<Oggettocarrello> oggettiCarrelli = c.getOggettoCarrello();

        for(Oggettocarrello og : oggettiCarrelli)
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
    @SneakyThrows
    public boolean modificaOggettoCarrello(RegistrationOrModifyProdottoRequest request)
    {
        Optional<Utente> utenteOptional = utenteRepo.findByUsernameAndPassword(request.getUsername(), request.getPassword());

        if (utenteOptional.isPresent() && (Util.roleControl(request.getUsername(), request.getPassword(), Ruolo.VENDITORE)) || (Util.roleControl(request.getUsername(), request.getPassword(), Ruolo.CLIENTEVENDITORE))) {

            Utente u = utenteOptional.get();

            Optional<Prodotto> prodottoOptional = prodottoRepo.findByNome(request.getNome());

            if (prodottoOptional.isPresent()) {
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

                else {
                    // L'utente non è autorizzato a modificare questo prodotto
                    throw new UnAuthorizedException("Non Autorizzato");
                }
            } else {
                // Prodotto non trovato

                throw new NotFoundException("Prodotto non trovato");
            }
        } else {
            // Utente non autorizzato o ruolo non corretto
            throw new UnAuthorizedException("Non Autorizzato");
        }

    }
    @SneakyThrows
    public boolean sottraiQuantita(SottraiQuantitaRequest request)
    {
        Optional<Utente> utenteOptional = utenteRepo.findByUsernameAndPassword(request.getUsername(), request.getPassword());

        if(utenteOptional.isPresent() && (Util.roleControl(request.getUsername(), request.getPassword(), Ruolo.CLIENTE)) || (Util.roleControl(request.getUsername(), request.getPassword(), Ruolo.CLIENTEVENDITORE)))
        {

            Optional<Oggettocarrello> oggettocarrello = oggettocarrelloRepo.findById(request.getIdoggettocarrello());

            if(oggettocarrello.isPresent())
            {
                Oggettocarrello o = oggettocarrello.get();
                if(o.getCarrello().getDataAcquisto() == null)
                {
                    o.setQuantita(request.getQuantita());
                    oggettocarrelloRepo.save(o);
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

        else
        {
            throw new UnAuthorizedException("Non autorizzato");
        }

    }
    @SneakyThrows
    public boolean eliminaoggettocarrello(EliminaOggettoCarrelloRequest request)
    {
        Optional<Utente> utenteOptional = utenteRepo.findByUsernameAndPassword(request.getUsername(), request.getPassword());

        if (utenteOptional.isPresent() && ((Util.roleControl(request.getUsername(), request.getPassword(), Ruolo.VENDITORE) || (Util.roleControl(request.getUsername(), request.getPassword(), Ruolo.CLIENTEVENDITORE)))))
        {
            Utente u = utenteOptional.get();
            Optional<Oggettocarrello> oggettocarrello = oggettocarrelloRepo.findById(request.getIdoggettocarrello());

            if (oggettocarrello.isPresent())
            {

                Oggettocarrello o = oggettocarrello.get();


                if(request.getIdoggettocarrello() == o.getIdoggettocarrello() && o.getCarrello().getDataAcquisto() == null) // da implementare un ulteriore controllo
                {
                    oggettocarrelloRepo.delete(o);
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

        else
        {
            throw new UnAuthorizedException("Non autorizzato");
        }

    }
}
