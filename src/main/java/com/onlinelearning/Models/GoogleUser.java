package com.onlinelearning.Models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GoogleUser {

    private String email;

    private boolean emailVerifired;

    private String name;

    private String pictureUrl;

    private String locale;

    private String familyName;

    private String givenName;

}
