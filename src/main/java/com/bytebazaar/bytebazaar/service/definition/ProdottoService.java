package com.bytebazaar.bytebazaar.service.definition;

import com.bytebazaar.bytebazaar.dto.request.RegistrationOrModifyProdottoRequest;
import com.bytebazaar.bytebazaar.exception.messaggiException.NotFoundException;
import com.bytebazaar.bytebazaar.exception.messaggiException.UnAuthorizedException;
import com.bytebazaar.bytebazaar.model.Utente;

public interface ProdottoService
{
    public boolean registraProdotto(Utente u, RegistrationOrModifyProdottoRequest request);

    public boolean modificaProdotto(Utente u, RegistrationOrModifyProdottoRequest request);




}
