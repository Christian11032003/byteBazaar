package com.bytebazaar.bytebazaar.service.definition;

import com.bytebazaar.bytebazaar.model.Prodotto;

public interface ProdottoService
{
    public Prodotto getByNome(String name);

    public void salva(Prodotto p);




}
