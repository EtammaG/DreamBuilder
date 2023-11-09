package com.neu.dreambuilder.service.common;

import com.neu.dreambuilder.entity.user.IUserDetails;

public interface SignService {
    String login(String username, String password, int type);

    void logout(IUserDetails userDetails);
}
