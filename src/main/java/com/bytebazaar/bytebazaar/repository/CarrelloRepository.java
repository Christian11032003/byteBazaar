package com.bytebazaar.bytebazaar.repository;

import com.bytebazaar.bytebazaar.model.Carrello;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarrelloRepository extends JpaRepository<Carrello,Integer>
{
    public Optional<Carrello> findCarrelloByUtente_UsernameAndUtente_PasswordAndDataAcquistoIsNull(String username, String password);

    public Optional<Carrello> findCarrelloByUtente_UsernameAndAndDataAcquistoIsNull(String username);

}
