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

    public List<Prodotto> findAllByUtenteIsNot(Utente u);

    @Query(nativeQuery = true, value = "SELECT utente.username AS username, prodotto.nome AS nome, prodotto.quantita AS quantita FROM utente JOIN prodotto ON prodotto.idutente = utente.id WHERE prodotto.id <> :idUtente")
    public List<Prodotto> findAllByUtente_Id(int idUtente);

    @Query(nativeQuery = true,value = "SELECT utente.username AS venditore, prodotto.nome AS prodotto_nome, oggettocarrello.quantita AS oggetto_quantita, carrello.id AS carrello_id FROM utente JOIN prodotto ON utente.id = prodotto.idutente JOIN oggettocarrello ON prodotto.id = oggettocarrello.idprodotto JOIN carrello ON oggettocarrello.idcarrello = carrello.id WHERE carrello.idutente = 3 AND carrello.dataacquisto IS NOT NULL")
    public List<Prodotto> getAllProductInKart(int idUtente);








}
