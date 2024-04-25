package com.bytebazaar.bytebazaar.dto.request.richiesta;

import com.bytebazaar.bytebazaar.model.Stato;
import lombok.Data;

@Data
public class AcceptOrRejectRequest
{
    private int idRichiesta;
    private Stato stato;
}
