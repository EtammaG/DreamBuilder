package com.neu.dreambuilder.entity.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class VolunteerDetails implements IUserDetails{
    
    private UserVolunteer userVolunteer;

    private List<String> authorities;



    public VolunteerDetails(UserVolunteer userVolunteer) {
        this.userVolunteer = userVolunteer;
        this.authorities = new ArrayList<>();
        this.authorities.add("VOLUNTEER");
        this.authorities.add("LOGIN");
    }

    @Override
    public Long getId() {
        return userVolunteer.getVolunteerId();
    }

    @Override
    public int getType() {
        return 3;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities.stream().map(SimpleGrantedAuthority::new).toList();
    }

    @Override
    public String getPassword() {
        return userVolunteer.getPassword();
    }

    @Override
    public String getUsername() {
        return userVolunteer.getUsername();
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