package com.neu.dreambuilder.entity.user;

import java.util.ArrayList;

public class VolunteerDetails extends IUserDetails {

    public VolunteerDetails(UserVolunteer userVolunteer) {
        setId(userVolunteer.getVolunteerId());
        setType(3);
        setPermissions(new ArrayList<>() {{
            add("VOLUNTEER");
            add("LOGIN");
        }});
        setPassword(userVolunteer.getPassword());
        setUsername(userVolunteer.getUsername());
        setAccountNonExpired(true);
        setAccountNonLocked(true);
        setCredentialsNonExpired(true);
        setEnabled(true);
    }

}