package com.bytebazaar.bytebazaar.utils;

import com.bytebazaar.bytebazaar.model.Ruolo;
import com.bytebazaar.bytebazaar.model.Utente;
import com.bytebazaar.bytebazaar.repository.UtenteRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Data
@Component
public class Util
{
    private static UtenteRepository utenteRepo;

    @Autowired
    public Util(UtenteRepository utenteRepo) {
        Util.utenteRepo = utenteRepo;
    }

    public static boolean roleControl(String username, String password, Ruolo ruolo){
        Optional<Utente> utenteOptional = utenteRepo.findByUsernameAndPassword(username,password);
        if(utenteOptional.isPresent())
        {
            Utente u = utenteOptional.get();
            return u.getRuolo().equals(ruolo);
        }

        return false;
    }
}


