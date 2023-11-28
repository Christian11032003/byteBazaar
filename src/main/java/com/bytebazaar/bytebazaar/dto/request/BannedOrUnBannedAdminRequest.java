package com.bytebazaar.bytebazaar.dto.request;

import lombok.Data;
@Data
public class BannedOrUnBannedAdminRequest {

    private String usernameAdmin;
    private String passwordAdmin;
    private int idutente;

}
