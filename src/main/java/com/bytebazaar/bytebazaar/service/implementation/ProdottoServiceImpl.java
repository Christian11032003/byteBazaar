package com.bytebazaar.bytebazaar.service.implementation;

import com.bytebazaar.bytebazaar.dto.request.ModificaProdottoRequest;
import com.bytebazaar.bytebazaar.dto.request.RegistrationProdottoRequest;
import com.bytebazaar.bytebazaar.model.Prodotto;
import com.bytebazaar.bytebazaar.model.Ruolo;
import com.bytebazaar.bytebazaar.model.Utente;
import com.bytebazaar.bytebazaar.repository.ProdottoRepository;
import com.bytebazaar.bytebazaar.repository.UtenteRepository;
import com.bytebazaar.bytebazaar.service.definition.ProdottoService;
import com.bytebazaar.bytebazaar.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdottoServiceImpl implements ProdottoService
{
    @Autowired
    private UtenteRepository utenteRepo;

    @Autowired
    private ProdottoRepository prodottoRepo;

    public boolean registraProdotto(RegistrationProdottoRequest request)
    {
        Optional<Utente> u = utenteRepo.findByUsernameAndPassword(request.getUsername(), request.getPassword());

        if(u.isPresent() && Util.roleControlSeller(request.getUsername(),request.getPassword(),Ruolo.VENDITORE))
        {

            Utente utSes = u.get();


            Prodotto p = new Prodotto();

            p.setUtente(utSes);
            p.setImmagineProdotto(request.getImmagine());
            p.setNome(request.getNome());
            p.setDescrizione(request.getDescrizione());
            p.setPrezzo(request.getPrezzo());
            p.setQuantita(request.getQuantita());

            p = prodottoRepo.save(p);

            return true;

        }

        else
        {
            return false;
        }

    }

    public boolean modificaProdotto(ModificaProdottoRequest request)
    {
        Optional<Utente> u = utenteRepo.findByUsernameAndPassword(request.getUsername(), request.getPassword());

        if (u.isPresent() && Util.roleControlSeller(request.getUsername(), request.getPassword(), Ruolo.VENDITORE)) {

            Utente utSes = u.get();

            Optional<Prodotto> prodottoOptional = prodottoRepo.findById(request.getIdprodotto());

            if (prodottoOptional.isPresent()) {
                Prodotto p = prodottoOptional.get();

                // Verifica che l'utente associato al prodotto sia lo stesso dell'utente autenticato
                if (p.getUtente().equals(utSes)) {
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
                } else {
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
