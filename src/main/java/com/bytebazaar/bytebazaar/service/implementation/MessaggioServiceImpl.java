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

        Optional<Messaggio> messaggioOptional = messaggioRepo.findMessaggioByUtente_IdutenteAndProdotto_IdProdotto(u.getIdutente(), p.getIdProdotto());

        if (messaggioOptional.isEmpty()) {
            // Creazione di un nuovo messaggio quando non ne esiste uno
            Messaggio m = new Messaggio();
            m.setUtente(u);
            m.setProdotto(p);
            m.setTestoMessaggio(request.getTestoMessaggio());
            m.setDataOraArrivo(LocalDateTime.now());
            m.setMittenteORdestinatario(true);
            messaggioRepo.save(m);
        }

        else
        {
            // Aggiornamento del messaggio esistente
            Messaggio m = messaggioOptional.get();
            Messaggio m2 = new Messaggio();
            m2.setUtente(m.getUtente());
            m2.setProdotto(p);
            m2.setTestoMessaggio(request.getTestoMessaggio());
            m2.setDataOraArrivo(LocalDateTime.now());

            if (u.getIdutente() == m.getUtente().getIdutente()) {
                m2.setMittenteORdestinatario(true);
            } else if (u.getIdutente() == m.getProdotto().getUtente().getIdutente()) {
                m2.setMittenteORdestinatario(false);
            } else {
                // Gestire il caso in cui la logica di confronto non è soddisfatta
                throw new RuntimeException("Logica di confronto non gestita correttamente.");
            }

            messaggioRepo.save(m2);
        }

        return true;
    }

}