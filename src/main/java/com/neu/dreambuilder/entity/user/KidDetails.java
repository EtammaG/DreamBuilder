package com.neu.dreambuilder.entity.user;

import java.util.ArrayList;

public class KidDetails extends IUserDetails {

    public KidDetails(UserKid userKid) {
        setId(userKid.getKidId());
        setType(2);
        setPermissions(new ArrayList<>() {{
            add("KID");
            add("LOGIN");
        }});
        setPassword(userKid.getPassword());
        setUsername(userKid.getUsername());
        setAccountNonExpired(true);
        setAccountNonLocked(true);
        setCredentialsNonExpired(true);
        setEnabled(true);
    }

}
