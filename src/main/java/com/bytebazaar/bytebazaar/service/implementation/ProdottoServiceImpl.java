package com.bytebazaar.bytebazaar.service.implementation;

import com.bytebazaar.bytebazaar.dto.request.RegistrationProdottoRequest;
import com.bytebazaar.bytebazaar.model.Prodotto;
import com.bytebazaar.bytebazaar.model.Utente;
import com.bytebazaar.bytebazaar.repository.UtenteRepository;
import com.bytebazaar.bytebazaar.service.definition.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class ProdottoServiceImpl implements ProdottoService
{
    @Autowired
    private UtenteRepository utenteRepo;
    public boolean registraProdotto(RegistrationProdottoRequest request)
    {
        Optional<Utente> u = utenteRepo.findByUsernameAndPassword(request.getUsername(), request.getPassword());

        if(u.isPresent())
        {
            Utente utSes = u.get();


            Prodotto p = new Prodotto();
            if(utSes.getIdutente() == p.getUtente().getIdutente())
            {
                p.setUtente(utSes);
                p.setImmagineProdotto(request.getImmagine());
                p.setNome(request.getNome());
                p.setDescrizione(request.getDescrizione());
                p.setPrezzo(request.getPrezzo());
                p.setQuantita(request.getQuantita());

                return true;

            }

            else {

                return false;

            }
        }

        else {
            return false;
        }

    }
}
