package pl.coderslab.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CurrentUser extends User { //obiekt rozszerzajacy domyslny obiekt User ze spring security (tworzony podczas autoryzacji UserDetailService
    // zrobiony w celu stworzenia pola user zgodnego z NASZĄ domeną

    private final pl.coderslab.model.User user;

    public CurrentUser(String login, String password, Collection<?
                extends GrantedAuthority> authorities, pl.coderslab.model.User user) {
        super(login, password, authorities);
        this.user = user;
    }
    public pl.coderslab.model.User getUser() {
        return user;
    }
}