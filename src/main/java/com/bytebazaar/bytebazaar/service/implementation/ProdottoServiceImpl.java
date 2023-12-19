package com.bytebazaar.bytebazaar.service.implementation;

import com.bytebazaar.bytebazaar.dto.request.RegistrationOrModifyProdottoRequest;
import com.bytebazaar.bytebazaar.model.*;
import com.bytebazaar.bytebazaar.repository.ProdottoRepository;
import com.bytebazaar.bytebazaar.repository.RichiestaRepository;
import com.bytebazaar.bytebazaar.repository.UtenteRepository;
import com.bytebazaar.bytebazaar.service.definition.ProdottoService;
import com.bytebazaar.bytebazaar.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdottoServiceImpl implements ProdottoService
{
    @Autowired
    private UtenteRepository utenteRepo;

    @Autowired
    private ProdottoRepository prodottoRepo;

    @Autowired
    private RichiestaRepository richiestaRepo;

    public boolean registraProdotto(RegistrationOrModifyProdottoRequest request)
    {
        Optional<Utente> utenteOptional = utenteRepo.findByUsernameAndPassword(request.getUsername(), request.getPassword());

        if(utenteOptional.isPresent() && ((Util.roleControlSeller(request.getUsername(),request.getPassword(),Ruolo.VENDITORE) || Util.roleControlSeller(request.getUsername(),request.getPassword(),Ruolo.CLIENTEVENDITORE))))
        {

            Utente u = utenteOptional.get();


            Optional<Richiesta> richiestaOptional = richiestaRepo.findByUtente_UsernameAndUtente_Password(request.getUsername(),request.getPassword());

            if(richiestaOptional.isPresent())
            {
                Richiesta r = richiestaOptional.get();

                List<Prodotto> prodottoList= u.getProdotto();

                boolean notExistProduct = prodottoList.stream().noneMatch(prodotto -> prodotto.getNome().equalsIgnoreCase(request.getNome()));



                if(r.getStato().name().contains("ACCETTATO") && notExistProduct)
                {
                    Prodotto p = new Prodotto();
                    p.setUtente(u);
                    p.setImmagineProdotto(request.getImmagine());
                    p.setNome(request.getNome());
                    p.setDescrizione(request.getDescrizione());
                    p.setPrezzo(request.getPrezzo());
                    p.setQuantita(request.getQuantita());
                    prodottoRepo.save(p);
                    return true;
                }

                if(r.getStato().name().contains("ACCETTATO") && !notExistProduct)
                {
                    System.out.println("Sono qui ");
                    Optional<Prodotto> optionalProdotto = prodottoRepo.findByNome(request.getNome());

                    if(optionalProdotto.isPresent())
                    {
                        System.out.println("entraaaa");
                        return modificaProdotto(request);
                    }

                    else
                    {
                        return false;
                    }



                }


            }


        }

        else
        {
            return false;
        }

        return false;

    }

    public boolean modificaProdotto(RegistrationOrModifyProdottoRequest request)
    {
        Optional<Utente> utenteOptional = utenteRepo.findByUsernameAndPassword(request.getUsername(), request.getPassword());

        if (utenteOptional.isPresent() && (Util.roleControlSeller(request.getUsername(), request.getPassword(), Ruolo.VENDITORE)) || (Util.roleControlCustomer(request.getUsername(), request.getPassword(), Ruolo.CLIENTEVENDITORE))) {

            Utente u = utenteOptional.get();

            Optional<Prodotto> prodottoOptional = prodottoRepo.findByNome(request.getNome());

            if (prodottoOptional.isPresent()) {
                Prodotto p = prodottoOptional.get();

                // Verifica che l'utente associato al prodotto sia lo stesso dell'utente autenticato
                if (p.getUtente().equals(u)) {
                    // Modifica solo i campi non nulli nella richiesta
                    if (request.getImmagine() != null)
                    {
                        System.out.println("modifico l'immagine");
                        p.setImmagineProdotto(request.getImmagine());
                    }
                    if (request.getNome() != null) {
                        System.out.println("modifico il nome");
                        p.setNome(request.getNome());
                    }
                    if (request.getDescrizione() != null) {
                        System.out.println("modifico la descrizione");
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
                    return false;
                }
            } else {
                // Prodotto non trovato
                return false;
            }
        } else {
            // Utente non autorizzato o ruolo non corretto
            return false;
        }

    }



}
