package com.bytebazaar.bytebazaar.repository;

import com.bytebazaar.bytebazaar.dto.request.LoginRequest;
import com.bytebazaar.bytebazaar.model.Carrello;
import com.bytebazaar.bytebazaar.model.Oggettocarrello;
import com.bytebazaar.bytebazaar.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarrelloRepository extends JpaRepository<Carrello,Integer>
{
    public Optional<Carrello> findCarrelloByUtente_UsernameAndUtente_PasswordAndDataAcquistoIsNull(String username, String password);


}
