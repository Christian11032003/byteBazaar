package com.bytebazaar.bytebazaar.dto.request;

import com.bytebazaar.bytebazaar.model.Stato;
import lombok.Data;

@Data
public class ChangeRequestAcceptRequest
{
    private String usernameAdmin;
    private String passwordAdmin;
    private int idrichiesta;
    private Stato stato;
}
