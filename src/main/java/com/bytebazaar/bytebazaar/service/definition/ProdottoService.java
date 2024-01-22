package com.bytebazaar.bytebazaar.service.definition;

import com.bytebazaar.bytebazaar.dto.request.InsertOrModifyProductRequest;
import com.bytebazaar.bytebazaar.model.Utente;

public interface ProdottoService
{
    public boolean registraProdotto(Utente u, InsertOrModifyProductRequest request);

    public boolean modificaProdotto(Utente u, InsertOrModifyProductRequest request);




}
