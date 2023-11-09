package com.neu.dreambuilder.entity.user;

import java.util.ArrayList;

public class DonorDetails extends IUserDetails {

    public DonorDetails(UserDonor userDonor) {
        setId(userDonor.getDonorId());
        setType(1);
        setPermissions(new ArrayList<>() {{
            add("DONOR");
            add("LOGIN");
        }});
        setPassword(userDonor.getPassword());
        setUsername(userDonor.getUsername());
        setAccountNonExpired(true);
        setAccountNonLocked(true);
        setCredentialsNonExpired(true);
        setEnabled(true);
    }

}
