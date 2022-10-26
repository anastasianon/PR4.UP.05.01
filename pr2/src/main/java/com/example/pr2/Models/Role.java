package com.example.pr2.Models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN, STUDENT;

    @Override
    public String getAuthority(){
        return name();
    }
}
