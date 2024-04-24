package com.bytebazaar.bytebazaar.service.definition;

import com.bytebazaar.bytebazaar.dto.request.InsertOrModifyProductRequest;
import com.bytebazaar.bytebazaar.model.Prodotto;
import com.bytebazaar.bytebazaar.model.Utente;

public interface ProdottoService
{
    public Prodotto getByNome(String name);

    public void salva(Prodotto p);




}
