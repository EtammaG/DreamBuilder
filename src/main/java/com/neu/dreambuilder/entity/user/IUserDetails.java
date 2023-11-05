package com.neu.dreambuilder.entity.user;

import org.springframework.security.core.userdetails.UserDetails;

public interface IUserDetails extends UserDetails {
    Long getId();

    int getType();
}
