package com.bytebazaar.bytebazaar.service.definition;

import com.bytebazaar.bytebazaar.model.Richiesta;


public interface RichiestaService
{

    //funzionalità per applicare il facade
    public Richiesta findByUtenteUsername(String username);
    public void salva(Richiesta r);
    public Richiesta findByIdrichiesta(int idRichiesta);

}
