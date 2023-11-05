package com.neu.dreambuilder.entity.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class KidDetails implements IUserDetails {

    private UserKid userKid;

    private List<String> authorities;

    public KidDetails(UserKid userKid) {
        this.userKid = userKid;
        this.authorities = new ArrayList<>();
        this.authorities.add("KID");
        this.authorities.add("LOGIN");
    }

    @Override
    public Long getId() {
        return userKid.getKidId();
    }

    @Override
    public int getType() {
        return 2;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities.stream().map(SimpleGrantedAuthority::new).toList();
    }

    @Override
    public String getPassword() {
        return userKid.getPassword();
    }

    @Override
    public String getUsername() {
        return userKid.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
