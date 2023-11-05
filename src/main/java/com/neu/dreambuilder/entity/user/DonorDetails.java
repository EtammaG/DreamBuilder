package com.neu.dreambuilder.entity.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DonorDetails implements IUserDetails {

    private UserDonor userDonor;

    private List<String> authorities;

    public DonorDetails(UserDonor userDonor) {
        this.userDonor = userDonor;
        this.authorities = new ArrayList<>();
        this.authorities.add("DONOR");
        this.authorities.add("LOGIN");
    }

    @Override
    public Long getId() {
        return userDonor.getDonorId();
    }

    @Override
    public int getType() {
        return 1;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities.stream().map(SimpleGrantedAuthority::new).toList();
    }

    @Override
    public String getPassword() {
        return userDonor.getPassword();
    }

    @Override
    public String getUsername() {
        return userDonor.getUsername();
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
