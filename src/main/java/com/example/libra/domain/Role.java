package com.example.libra.domain;

import org.springframework.security.core.GrantedAuthority;

//enum - перечисление
public enum Role implements GrantedAuthority {
    ADMIN, USER, AUTHOR, REC, MODER;

    @Override
    public String getAuthority() {
        return name();
    }

}
