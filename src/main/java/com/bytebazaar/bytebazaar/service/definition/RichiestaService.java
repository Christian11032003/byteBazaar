package com.bytebazaar.bytebazaar.service.definition;

import com.bytebazaar.bytebazaar.model.Utente;
import com.bytebazaar.bytebazaar.repository.RichiestaRepository;
import com.bytebazaar.bytebazaar.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public interface RichiestaService
{
    public boolean registrazioneRichiesta(Utente u);

    public boolean changeRequestAccept(boolean stato);

}
