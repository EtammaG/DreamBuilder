package com.neu.dreambuilder.service.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.neu.dreambuilder.entity.user.DonorDetails;
import com.neu.dreambuilder.entity.user.UserDonor;
import com.neu.dreambuilder.mapper.user.UserDonorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("donorDetailsServiceImpl")
public class DonorDetailsServiceImpl implements UserDetailsService {

    private final UserDonorMapper userDonorMapper;

    @Autowired
    public DonorDetailsServiceImpl(UserDonorMapper userDonorMapper) {
        this.userDonorMapper = userDonorMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<UserDonor> wrapper = new LambdaQueryWrapper<>();
        wrapper
                .eq(UserDonor::getUsername, username);
        return new DonorDetails(userDonorMapper.selectOne(wrapper));
    }
}
