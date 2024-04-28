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

    public Optional<Prodotto> findById(int idProdotto);
    
    public List<Prodotto> findAllByUtente_Id(int idUtente);

    public List<Prodotto> findAllByUtenteIsNot(Utente u);

    @Query(nativeQuery = true,value = "select * from oggetto_carrello join carrello where carrello.idutente = :idUtente and carrello.dataAcquisto != null group by id_prodotto")
    public List<Prodotto> findAllProductInKart(int idUtente);








}
