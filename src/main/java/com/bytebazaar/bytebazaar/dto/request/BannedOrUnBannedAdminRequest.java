package com.bytebazaar.bytebazaar.dto.request;

import lombok.Data;
@Data
public class BannedOrUnBannedAdminRequest {

    private String username;
    private String password;
    private int id;

}
