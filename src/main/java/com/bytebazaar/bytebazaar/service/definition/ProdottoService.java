package com.bytebazaar.bytebazaar.service.definition;

import com.bytebazaar.bytebazaar.dto.request.RegistrationOrModifyProdottoRequest;
import com.bytebazaar.bytebazaar.exception.messaggiException.NotFoundException;
import com.bytebazaar.bytebazaar.exception.messaggiException.UnAuthorizedException;

public interface ProdottoService
{
    public boolean registraProdotto(RegistrationOrModifyProdottoRequest request) throws UnAuthorizedException, NotFoundException;

    public boolean modificaProdotto(RegistrationOrModifyProdottoRequest request) throws UnAuthorizedException, NotFoundException;




}
