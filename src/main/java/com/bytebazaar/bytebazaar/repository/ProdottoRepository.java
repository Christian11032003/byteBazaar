package com.bytebazaar.bytebazaar.repository;

import com.bytebazaar.bytebazaar.model.Condizione;
import com.bytebazaar.bytebazaar.model.Prodotto;
import com.bytebazaar.bytebazaar.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

// Definizione dell'interfaccia ProdottoRepository che estende JpaRepository per l'entità Prodotto con chiave primaria di tipo Integer
public interface ProdottoRepository extends JpaRepository<Prodotto, Integer> {

    /**
     * Metodo per trovare un prodotto per nome.
     * Restituisce un Optional contenente il Prodotto se trovato, altrimenti un Optional vuoto.
     *
     * @param nome il nome del prodotto da cercare
     * @return un Optional contenente il Prodotto se trovato, altrimenti un Optional vuoto
     */
    public Optional<Prodotto> findByNome(String nome);

    /**
     * Metodo per trovare un prodotto per ID.
     * Restituisce un Optional contenente il Prodotto se trovato, altrimenti un Optional vuoto.
     *
     * @param idProdotto l'ID del prodotto da cercare
     * @return un Optional contenente il Prodotto se trovato, altrimenti un Optional vuoto
     */
    public Optional<Prodotto> findById(int idProdotto);

    /**
     * Metodo per trovare tutti i prodotti che non appartengono a un determinato utente e hanno una determinata condizione.
     * Restituisce una lista di prodotti.
     *
     * @param u l'utente proprietario dei prodotti da escludere
     * @param condizione la condizione dei prodotti da cercare
     * @return una lista di prodotti che soddisfano i criteri specificati
     */
    public List<Prodotto> findAllByUtenteIsNotAndCondizione(Utente u, Condizione condizione);

    /**
     * Query nativa per trovare tutti i prodotti di un utente specifico tramite l'ID utente.
     * Restituisce una lista di prodotti associati all'utente.
     *
     * @param idUtente l'ID dell'utente di cui trovare i prodotti
     * @return una lista di prodotti associati all'utente specificato
     */
    @Query(nativeQuery = true, value = "SELECT utente.username AS username, prodotto.id as id, prodotto.nome AS nome, prodotto.descrizione as descrizione, prodotto.prezzo as prezzo, prodotto.quantita AS quantita, prodotto.condizione as condizione, prodotto.idutente AS idutente FROM utente JOIN prodotto ON prodotto.idutente = utente.id WHERE utente.id = :idUtente")
    public List<Prodotto> trovaProdottiUser(int idUtente);

    /**
     * Query nativa per trovare tutti i prodotti nel carrello di un utente specifico tramite l'ID utente
     * e dove la data di acquisto non è nulla.
     * Restituisce una lista di prodotti nel carrello dell'utente con data di acquisto non nulla.
     *
     * @param idUtente l'ID dell'utente di cui trovare i prodotti nel carrello
     * @return una lista di prodotti nel carrello dell'utente con data di acquisto non nulla
     */
    @Query(nativeQuery = true, value = "SELECT utente.username AS username, prodotto.id as id, prodotto.nome, prodotto.descrizione as descrizione, prodotto.prezzo as prezzo, prodotto.quantita AS quantita, prodotto.condizione as condizione, prodotto.idutente AS idutente FROM utente JOIN prodotto ON utente.id = prodotto.idutente JOIN oggettocarrello ON prodotto.id = oggettocarrello.idprodotto JOIN carrello ON oggettocarrello.idcarrello = carrello.id WHERE carrello.idutente = :idUtente AND carrello.dataacquisto IS NOT NULL")
    public List<Prodotto> getAllProductInKart(int idUtente);
}
