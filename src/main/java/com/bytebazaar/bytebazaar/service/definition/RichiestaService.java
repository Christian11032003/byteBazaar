package com.bytebazaar.bytebazaar.service.definition;

import com.bytebazaar.bytebazaar.model.Richiesta;

import java.util.Optional;


public interface RichiestaService
{

    //funzionalità per applicare il facade
    public Optional<Richiesta> findByUtenteUsername(String username);
    public void salva(Richiesta r);
    public Richiesta findByIdrichiesta(int idRichiesta);

}
