package com.bytebazaar.bytebazaar.service.implementation;

import com.bytebazaar.bytebazaar.dto.request.RegistrationUtenteRequest;
import com.bytebazaar.bytebazaar.model.Utente;
import com.bytebazaar.bytebazaar.repository.UtenteRepository;
import com.bytebazaar.bytebazaar.service.definition.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtenteServiceImpl implements UtenteService
{

    @Autowired
    private UtenteRepository utenteRepo;
    public boolean registrazioneUtente(RegistrationUtenteRequest request)
    {
        Utente u = new Utente();
        u.setNome(request.getNome());
        u.setCognome(request.getCognome());
        u.setEmail(request.getEmail());
        u.setUsername(request.getUsername());
        u.setPassword(request.getPassword());
        u.setRuolo(request.getRuolo());

        if (!(request.getPassword().equals(request.getPasswordRipetuta()))) {
            return false;
        }
        u = utenteRepo.save(u);
        return true;
    }
}
