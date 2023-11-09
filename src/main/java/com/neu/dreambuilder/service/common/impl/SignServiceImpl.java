package com.neu.dreambuilder.service.common.impl;

import com.alibaba.fastjson.JSON;
import com.neu.dreambuilder.common.utils.JwtUtil;
import com.neu.dreambuilder.entity.user.IUserDetails;
import com.neu.dreambuilder.service.common.SignService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;

@Service
public class SignServiceImpl implements SignService {

    @Resource(name = "donorAuthManager")
    private AuthenticationManager donorAuthManager;

    @Resource(name = "kidAuthManager")
    private AuthenticationManager kidAuthManager;

    @Resource(name = "volunteerAuthManager")
    private AuthenticationManager volunteerAuthManager;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Value("${dream-builder.redis.prefix.login-donor}")
    private String DONOR_KEY_PREFIX;

    @Value("${dream-builder.redis.prefix.login-kid}")
    private String KID_KEY_PREFIX;

    @Value("${dream-builder.redis.prefix.login-volunteer}")
    private String VOLUNTEER_KEY_PREFIX;

    @Value("${dream-builder.sys.sign-in-duration}")
    private Duration SIGNIN_DURATION;

    @Override
    public String login(String username, String password, int type) {
        System.out.println(username + password);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        String keyPrefix;
        Authentication authenticate;
        switch (type) {
            case 1 -> {
                authenticate = donorAuthManager.authenticate(token);
                keyPrefix = DONOR_KEY_PREFIX;
            }
            case 2 -> {
                authenticate = kidAuthManager.authenticate(token);
                keyPrefix = KID_KEY_PREFIX;
            }
            case 3 -> {
                authenticate = volunteerAuthManager.authenticate(token);
                keyPrefix = VOLUNTEER_KEY_PREFIX;
            }
            default -> {
                return null;
            }
        }
        IUserDetails principal = (IUserDetails) authenticate.getPrincipal();
        String key = keyPrefix + principal.getId();
        String jwt = JwtUtil.createJWT(key);
        stringRedisTemplate.opsForValue().set(
                key,
                JSON.toJSONString(principal),
                SIGNIN_DURATION
        );
        return jwt;
    }

    @Override
    public void logout(IUserDetails userDetails) {
        String keyPrefix = switch (userDetails.getType()) {
            case 1 -> DONOR_KEY_PREFIX;
            case 2 -> KID_KEY_PREFIX;
            case 3 -> VOLUNTEER_KEY_PREFIX;
            default -> null;
        };
        stringRedisTemplate.delete(keyPrefix + userDetails.getId());
    }
}
