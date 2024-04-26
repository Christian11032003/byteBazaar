package com.bytebazaar.bytebazaar.repository;

import com.bytebazaar.bytebazaar.model.Prodotto;
import com.bytebazaar.bytebazaar.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProdottoRepository extends JpaRepository<Prodotto,Integer>
{
    public Optional<Prodotto> findByNome(String nome);

    public Optional<Prodotto> findByIdProdotto(int idProdotto);

    public List<Prodotto> findAllByUtente(Utente u);

    public List<Prodotto> findAllByUtenteIsNot(Utente u);

    public List<Prodotto> findAllByUtente_Idutente(int idUtente);

    @Query(nativeQuery = true,value = "select * from OggettoCarrello join Carrello where Carrello.idUtente = :idUtente and Carrello.dataAcquisto != null group by idProdotto")
    public List<Prodotto> findAllProductInKart(int idUtente);








}
