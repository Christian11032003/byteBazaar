package com.bytebazaar.bytebazaar.dto.request;

import com.bytebazaar.bytebazaar.model.Stato;
import lombok.Data;

@Data
public class ChangeRequestAcceptRequest
{
    private int idrichiesta;
    private Stato stato;
}
