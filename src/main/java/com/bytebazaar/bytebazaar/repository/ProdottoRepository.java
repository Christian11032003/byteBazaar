package com.bytebazaar.bytebazaar.repository;

import com.bytebazaar.bytebazaar.model.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdottoRepository extends JpaRepository<Prodotto,Integer>
{
    public Prodotto findByNome(String nome);

}
