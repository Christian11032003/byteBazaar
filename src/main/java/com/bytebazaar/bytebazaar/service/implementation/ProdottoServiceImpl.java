package com.bytebazaar.bytebazaar.service.implementation;

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
}
