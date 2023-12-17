package com.bytebazaar.bytebazaar.utils;

import com.bytebazaar.bytebazaar.dto.request.RegistrationUtenteRequest;
import com.bytebazaar.bytebazaar.model.Ruolo;
import com.bytebazaar.bytebazaar.model.Utente;
import com.bytebazaar.bytebazaar.repository.UtenteRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
public class Util
{
    private static UtenteRepository utenteRepo;

    public Util(UtenteRepository utenteRepo) {
        Util.utenteRepo = utenteRepo;
    }

    public static boolean roleControlAdmin(String username, String password, Ruolo ruolo){
        Utente u = utenteRepo.findByUsernameAndPasswordAndRuolo(username,password,Ruolo.ADMIN).orElse(null);
        if(u==null)return false;
        return u.getRuolo().equals(ruolo);
    }

    public static boolean roleControlSeller(String username, String password, Ruolo ruolo){
        Utente u = utenteRepo.findByUsernameAndPasswordAndRuolo(username,password,Ruolo.VENDITORE).orElse(null);
        if(u==null)return false;
        return u.getRuolo().equals(ruolo);
    }

    public static boolean roleControlSellerClient(String username, String password, Ruolo ruolo){
        Utente u = utenteRepo.findByUsernameAndPasswordAndRuolo(username,password,Ruolo.CLIENTEVENDITORE).orElse(null);
        if(u==null)return false;
        return u.getRuolo().equals(ruolo);
    }

    public static boolean roleControlCustomer(String username, String password, Ruolo ruolo){
        Utente u = utenteRepo.findByUsernameAndPasswordAndRuolo(username,password,Ruolo.CLIENTE).orElse(null);
        if(u==null)return false;
        return u.getRuolo().equals(ruolo);
    }



}
