package com.bytebazaar.bytebazaar.dto.request;

import com.bytebazaar.bytebazaar.model.Stato;
import lombok.Data;

@Data
public class AcceptOrRejectRequestDTO
{
    private int idRichiesta;
    private Stato stato;
}
