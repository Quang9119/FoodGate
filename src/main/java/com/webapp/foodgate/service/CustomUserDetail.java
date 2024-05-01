package com.webapp.foodgate.service;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomUserDetail extends org.springframework.security.core.userdetails.User{
    public String getId() {
        return id;
    }
    private String id;
    public CustomUserDetail(String username,
                            String password,
                            Collection<? extends GrantedAuthority> authorities, String id) {
        super(username, password, authorities);
        this.id = id;
    }

}
