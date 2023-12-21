package com.bytebazaar.bytebazaar.service.implementation;

import com.bytebazaar.bytebazaar.dto.request.AggiungiProdottoInCarrelloRequest;
import com.bytebazaar.bytebazaar.dto.request.LoginRequest;
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
        Optional<Utente> u = utenteRepo.findByUsernameAndPassword(request.getUsername(), request.getPassword());

        if (u.isPresent() &&
                ((Util.roleControl(request.getUsername(), request.getPassword(), Ruolo.CLIENTEVENDITORE)) ||
                        (Util.roleControl(request.getUsername(), request.getPassword(), Ruolo.CLIENTE)))) {

            Utente utente = u.get();

            // Find the carrello with a date, or create a new one
            Carrello carrello = utente.getCarrello().stream()
                    .filter(c -> c.getDataAcquisto() == null)
                    .findFirst()
                    .orElseGet(() -> {
                        Carrello newCarrello = new Carrello();
                        newCarrello.setUtente(utente);
                        newCarrello.setDataAcquisto(null);
                        return carrelloRepo.save(newCarrello);
                    });

            Optional<Prodotto> prodottoOptional = prodottoRepo.findByNome(request.getNomeProdotto());

            if(prodottoOptional.isEmpty())
            {
                throw new NotFoundException("Prodotto non trovato");
            }

            else
            {
                Prodotto p = prodottoOptional.get();


                Oggettocarrello oggettoCarrello = new Oggettocarrello();
                oggettoCarrello.setProdotto(p);
                oggettoCarrello.setQuantita(request.getQuantita());


                // Set the relationship between Carrello and Oggettocarrello
                oggettoCarrello.setCarrello(carrello);

                // Save the Oggettocarrello and update the Carrello to the database using the repositories
                oggettocarrelloRepo.save(oggettoCarrello);
                carrelloRepo.save(carrello);

                return true; // Successfully added to the carrello
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
}
