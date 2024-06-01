package com.bytebazaar.bytebazaar.repository;

import com.bytebazaar.bytebazaar.model.Condizione;
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

    public List<Prodotto> findAllByUtenteIsNotAndCondizione(Utente u, Condizione condizione);

    @Query(nativeQuery = true, value = "SELECT utente.username AS username, prodotto.id as id, prodotto.nome AS nome, prodotto.descrizione as descrizione, prodotto.prezzo as prezzo, prodotto.quantita AS quantita, prodotto.condizione as condizione, prodotto.idutente AS idutente FROM utente JOIN prodotto ON prodotto.idutente = utente.id WHERE utente.id = :idUtente")
    public List<Prodotto> trovaProdottiUser(int idUtente);

    @Query(nativeQuery = true,value = "SELECT utente.username AS username, prodotto.id as id , prodotto.nome, prodotto.descrizione as descrizione, prodotto.prezzo as prezzo, prodotto.quantita AS quantita, prodotto.condizione as condizione, prodotto.idutente AS idutente, FROM utente JOIN prodotto ON utente.id = prodotto.idutente JOIN oggettocarrello ON prodotto.id = oggettocarrello.idprodotto JOIN carrello ON oggettocarrello.idcarrello = carrello.id WHERE carrello.idutente = idUtente AND carrello.dataacquisto IS NOT NULL")
    public List<Prodotto> getAllProductInKart(int idUtente);








}
