package com.bytebazaar.bytebazaar.service.implementation;

import com.bytebazaar.bytebazaar.dto.request.AggiungiProdottoInCarrelloRequest;
import com.bytebazaar.bytebazaar.dto.request.LoginRequest;
import com.bytebazaar.bytebazaar.model.*;
import com.bytebazaar.bytebazaar.repository.*;
import com.bytebazaar.bytebazaar.service.definition.CarrelloService;
import com.bytebazaar.bytebazaar.service.definition.OggettocarrelloService;
import com.bytebazaar.bytebazaar.utils.Util;
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
    public boolean aggiungiAlCarrello(AggiungiProdottoInCarrelloRequest request)
    {
        Optional<Utente> u = utenteRepo.findByUsernameAndPassword(request.getUsername(), request.getPassword());

        if (u.isPresent() &&
                ((Util.roleControlSellerClient(request.getUsername(), request.getPassword(), Ruolo.CLIENTEVENDITORE)) ||
                        (Util.roleControlCustomer(request.getUsername(), request.getPassword(), Ruolo.CLIENTE)))) {

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

            Prodotto p = prodottoRepo.findByNome(request.getNomeProdotto());

            if(p == null)
            {
                return false;
            }


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

        else {
            return false;
        }

    }

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
                return true;
            }

            else
            {
                return false;
            }


        }

        return false;

    }
}
