package com.bytebazaar.bytebazaar.repository;

import com.bytebazaar.bytebazaar.model.Prodotto;
import com.bytebazaar.bytebazaar.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProdottoRepository extends JpaRepository<Prodotto,Integer>
{
    public Optional<Prodotto> findByNome(String nome);

    public Optional<Prodotto> findByIdProdotto(int idprodotto);

    public List<Prodotto> findAllByUtente_UsernameIsNot(String username);

    public List<Prodotto> findAllByUtente_UsernameAndUtente_Password(String username, String password);




}
