package com.bytebazaar.bytebazaar.service.implementation;

import com.bytebazaar.bytebazaar.dto.request.MandaMessaggioRequest;
import com.bytebazaar.bytebazaar.model.Messaggio;
import com.bytebazaar.bytebazaar.model.Prodotto;
import com.bytebazaar.bytebazaar.model.Utente;
import com.bytebazaar.bytebazaar.repository.MessaggioRepository;
import com.bytebazaar.bytebazaar.repository.ProdottoRepository;
import com.bytebazaar.bytebazaar.repository.UtenteRepository;
import com.bytebazaar.bytebazaar.service.definition.MessaggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MessaggioServiceImpl implements MessaggioService
{

    @Autowired
    MessaggioRepository messaggioRepo;

    @Autowired
    UtenteRepository utenteRepo;

    @Autowired
    ProdottoRepository prodottoRepo;

    public boolean mandaMessaggio(MandaMessaggioRequest request) {
        Optional<Utente> optionalUtente = utenteRepo.findByUsernameAndPassword(request.getUsername(), request.getPassword());

        if (optionalUtente.isEmpty()) {
            return false;
        }

        Utente u = optionalUtente.get();

        Optional<Prodotto> prodottoOptional = prodottoRepo.findByIdProdotto(request.getIdprodotto());

        if (prodottoOptional.isEmpty()) {
            return false;
        }

        Prodotto p = prodottoOptional.get();

        List<Prodotto> prodottoList = u.getProdotto();
        Messaggio m2 = new Messaggio();
        m2.setUtente(u);
        m2.setTestoMessaggio(request.getTestoMessaggio());
        m2.setDataOraArrivo(LocalDateTime.now());


        if(prodottoList.contains(p))
        {
            System.out.println("sono il cliente");
            m2.setMittenteORdestinatario(false);
        }

        else
        {
            System.out.println("sono il venditore");
            m2.setMittenteORdestinatario(true);
        }

        messaggioRepo.save(m2);


        return true;
    }

}