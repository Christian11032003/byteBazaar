package com.bytebazaar.bytebazaar.service.definition;

import com.bytebazaar.bytebazaar.dto.request.ModificaProdottoRequest;
import com.bytebazaar.bytebazaar.dto.request.RegistrationProdottoRequest;
import com.bytebazaar.bytebazaar.model.Prodotto;

import java.util.Optional;

public interface ProdottoService
{
    public boolean registraProdotto(RegistrationProdottoRequest request);

    public boolean modificaProdotto(ModificaProdottoRequest request);




}
