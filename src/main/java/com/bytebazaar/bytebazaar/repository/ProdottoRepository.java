package com.bytebazaar.bytebazaar.repository;

import com.bytebazaar.bytebazaar.dto.request.RegistrationProdottoRequest;
import com.bytebazaar.bytebazaar.model.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProdottoRepository extends JpaRepository<Prodotto,Integer>
{
    public Prodotto findByNome(String nome);

    public Optional<Prodotto> findByIdProdotto(int id);


}
