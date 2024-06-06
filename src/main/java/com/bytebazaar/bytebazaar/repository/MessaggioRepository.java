package com.bytebazaar.bytebazaar.repository;

import com.bytebazaar.bytebazaar.model.Messaggio;
import com.bytebazaar.bytebazaar.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


// Definizione dell'interfaccia MessaggioRepository che estende JpaRepository per l'entità Messaggio con chiave primaria di tipo Integer
public interface MessaggioRepository extends JpaRepository<Messaggio, Integer> {

    /**
     * Metodo per trovare tutti i messaggi di un utente tramite l'ID dell'utente.
     * Restituisce una lista di Messaggio.
     *
     * @param idUtente l'ID dell'utente di cui trovare i messaggi
     * @return una lista di Messaggio associati all'utente specificato
     */
    public List<Messaggio> findAllByUtente_Id(int idUtente);

    /**
     * Metodo per trovare tutti i messaggi di un utente tramite l'entità Utente.
     * Restituisce una lista di Messaggio.
     *
     * @param u l'utente di cui trovare i messaggi
     * @return una lista di Messaggio associati all'utente specificato
     */
    public List<Messaggio> findAllByUtente(Utente u);
}
