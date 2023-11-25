package com.bytebazaar.bytebazaar.service.implementation;

import com.bytebazaar.bytebazaar.model.Richiesta;
import com.bytebazaar.bytebazaar.model.Ruolo;
import com.bytebazaar.bytebazaar.model.Stato;
import com.bytebazaar.bytebazaar.model.Utente;
import com.bytebazaar.bytebazaar.repository.RichiestaRepository;
import com.bytebazaar.bytebazaar.service.definition.RichiestaService;
import com.bytebazaar.bytebazaar.service.definition.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RichiestaServiceImpl implements RichiestaService
{

    @Autowired
    private RichiestaRepository richiestaRepo;

    @Autowired
    private UtenteService serviceUtente;
    public boolean registrazioneRichiesta(Utente u)
    {

        Richiesta r = new Richiesta();
        r.setUtente(u);
        r.setStato(Stato.RICHIESTA);
        if(r.getUtente().getIdutente() == u.getIdutente())
        {
            r = richiestaRepo.save(r);
            return true;
        }

        return false;
    }

    public boolean changeRequestAccept(boolean stato)
    {
        Richiesta r = new Richiesta();
        Utente u = r.getUtente();
        if(stato && serviceUtente.roleControl(u.getUsername(),u.getPassword(), Ruolo.ADMIN) && r.getUtente().getIdutente() == u.getIdutente())
        {
            r.setStato(Stato.ACCETTATO);
            if(u.getRuolo() == Ruolo.CLIENTE)
            {
                u.setRuolo(Ruolo.CLIENTEVENDITORE);
            }
        }

        else
        {
            r.setStato(Stato.RIFIUTATO);
            if(u.getRuolo() == Ruolo.CLIENTEVENDITORE)
            {
                u.setRuolo(Ruolo.CLIENTE);
            }
        }
        return true;
    }
}
