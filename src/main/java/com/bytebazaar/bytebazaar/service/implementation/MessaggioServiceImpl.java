package com.bytebazaar.bytebazaar.service.implementation;

import com.bytebazaar.bytebazaar.dto.request.MandaMessaggioRequest;
import com.bytebazaar.bytebazaar.model.Messaggio;
import com.bytebazaar.bytebazaar.model.Prodotto;
import com.bytebazaar.bytebazaar.model.Utente;
import com.bytebazaar.bytebazaar.repository.MessaggioRepository;
import com.bytebazaar.bytebazaar.repository.ProdottoRepository;
import com.bytebazaar.bytebazaar.repository.UtenteRepository;
import com.bytebazaar.bytebazaar.service.definition.MessaggioService;
import com.bytebazaar.bytebazaar.service.definition.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public boolean mandaMessaggio(MandaMessaggioRequest request)
    {

        Optional<Utente> utenteOptional = utenteRepo.findByUsernameAndPassword(request.getUsername(), request.getPassword());

        if(utenteOptional.isPresent())
        {
            Utente u = utenteOptional.get();
            Optional<Prodotto> optionalProdotto = prodottoRepo.findByIdProdotto(request.getIdprodotto());

            if(optionalProdotto.isPresent())
            {
                Prodotto p = optionalProdotto.get();

                if(u.getIdutente() != p.getUtente().getIdutente())
                {
                    Messaggio m = new Messaggio();

                    m.setProdotto(p);
                    m.setMittente(u.getUsername());
                    m.setTestoMessaggio(request.getTestoMessaggio());
                    m.setDataOraArrivo(LocalDateTime.now());
                    m.setMittenteORdestinatario(true);

                    m = messaggioRepo.save(m);
                    return true;
                }

            }

        }

        return false;
    }



}
