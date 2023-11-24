package com.bytebazaar.bytebazaar.service.implementation;

import com.bytebazaar.bytebazaar.model.Richiesta;
import com.bytebazaar.bytebazaar.model.Utente;
import com.bytebazaar.bytebazaar.repository.RichiestaRepository;
import com.bytebazaar.bytebazaar.service.definition.RichiestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RichiestaServiceImpl implements RichiestaService
{

    @Autowired
    private RichiestaRepository richiestaRepo;
    public boolean registrazioneRichiesta(Utente u)
    {

        Richiesta r = new Richiesta();
        r.setUtente(u);
        if(r.getUtente().getIdutente() == u.getIdutente())
        {
            r = richiestaRepo.save(r);
            return true;
        }

        return false;
    }
}
