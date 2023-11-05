package com.neu.dreambuilder.service.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.neu.dreambuilder.entity.user.VolunteerDetails;
import com.neu.dreambuilder.entity.user.UserVolunteer;
import com.neu.dreambuilder.mapper.user.UserVolunteerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("volunteerDetailsServiceImpl")
public class VolunteerDetailsServiceImpl implements UserDetailsService {

    private final UserVolunteerMapper userVolunteerMapper;

    @Autowired
    public VolunteerDetailsServiceImpl(UserVolunteerMapper userVolunteerMapper) {
        this.userVolunteerMapper = userVolunteerMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<UserVolunteer> wrapper = new LambdaQueryWrapper<>();
        wrapper
                .eq(UserVolunteer::getUsername, username);
        return new VolunteerDetails(userVolunteerMapper.selectOne(wrapper));
    }
}
