package com.bytebazaar.bytebazaar.service.definition;

import com.bytebazaar.bytebazaar.dto.request.RegistrationOrModifyProdottoRequest;

public interface ProdottoService
{
    public boolean registraProdotto(RegistrationOrModifyProdottoRequest request);

    public boolean modificaProdotto(RegistrationOrModifyProdottoRequest request);




}
