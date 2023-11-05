package com.neu.dreambuilder.service.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.neu.dreambuilder.entity.user.KidDetails;
import com.neu.dreambuilder.entity.user.UserKid;
import com.neu.dreambuilder.mapper.user.UserKidMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("kidDetailsServiceImpl")
public class KidDetailsServiceImpl implements UserDetailsService {

    private final UserKidMapper userKidMapper;

    @Autowired
    public KidDetailsServiceImpl(UserKidMapper userKidMapper) {
        this.userKidMapper = userKidMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<UserKid> wrapper = new LambdaQueryWrapper<>();
        wrapper
                .eq(UserKid::getUsername, username);
        return new KidDetails(userKidMapper.selectOne(wrapper));
    }
}
