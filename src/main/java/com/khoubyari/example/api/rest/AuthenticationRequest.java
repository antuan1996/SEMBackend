package com.khoubyari.example.api.rest;

import java.io.Serializable;

public class AuthenticationRequest implements Serializable {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
