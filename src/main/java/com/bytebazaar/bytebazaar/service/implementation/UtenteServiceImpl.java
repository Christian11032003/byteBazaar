package com.bytebazaar.bytebazaar.service.implementation;

import com.bytebazaar.bytebazaar.dto.request.RegistrationUtenteRequest;
import com.bytebazaar.bytebazaar.model.Ruolo;
import com.bytebazaar.bytebazaar.model.Utente;
import com.bytebazaar.bytebazaar.repository.RichiestaRepository;
import com.bytebazaar.bytebazaar.repository.UtenteRepository;
import com.bytebazaar.bytebazaar.service.definition.RichiestaService;
import com.bytebazaar.bytebazaar.service.definition.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UtenteServiceImpl implements UtenteService
{

    @Autowired
    private UtenteRepository utenteRepo;
    @Autowired
    private RichiestaService serviceRichiesta;
    public boolean registrazioneUtente(RegistrationUtenteRequest request)
    {
        Utente u = new Utente();
        u.setNome(request.getNome());
        u.setCognome(request.getCognome());
        u.setEmail(request.getEmail());
        u.setUsername(request.getUsername());
        u.setPassword(request.getPassword());
        u.setRuolo(request.getRuolo());



        if ((request.getPassword().equals(request.getPasswordRipetuta()))) {
            u = utenteRepo.save(u);
            if((request.getRuolo() == Ruolo.VENDITORE) || (request.getRuolo() == Ruolo.CLIENTEVENDITORE))
            {
                serviceRichiesta.registrazioneRichiesta(u);
            }
            return true;
        }

        return false;
    }
}
