package com.bytebazaar.bytebazaar.repository;

import com.bytebazaar.bytebazaar.dto.request.LoginRequest;
import com.bytebazaar.bytebazaar.model.Carrello;
import com.bytebazaar.bytebazaar.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrelloRepository extends JpaRepository<Carrello,Integer>
{
}
