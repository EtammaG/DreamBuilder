package com.neu.dreambuilder.filter;

import com.alibaba.fastjson.JSON;
import com.neu.dreambuilder.common.utils.BaseContext;
import com.neu.dreambuilder.common.utils.JwtUtil;
import com.neu.dreambuilder.entity.user.IUserDetails;
import com.neu.dreambuilder.exception.bean.CustomException;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;

@Slf4j
@Component
public class JwtAuthTokenFilter extends OncePerRequestFilter {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Value("${dream-builder.redis.prefix.limit-id}")
    private String REDIS_PREFIX_LIMIT_ID;

    @Value("${dream-builder.sys.sign-in-duration}")
    private Duration SIGNIN_DURATION;

    @Value("${dream-builder.sys.id-limit-times-in-a-minute}")
    private int LIMIT_TIMES_IN_A_MINUTE;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        String redisKey;
        try {
             redisKey = JwtUtil.parseJWT(token).getBody().getSubject();
        }catch (JwtException e) {
            throw new CustomException("illegal token");
        }
        IUserDetails userDetails = JSON.parseObject(stringRedisTemplate.opsForValue().get(redisKey), IUserDetails.class);
        if (userDetails == null) {
            log.warn("illegal token");
            filterChain.doFilter(request, response);
            return;
        }

        String redisLimitKey = REDIS_PREFIX_LIMIT_ID + redisKey;
        String count = stringRedisTemplate.opsForValue().get(redisLimitKey);
        if (count == null) {
            stringRedisTemplate.opsForValue().set(redisLimitKey, "1", Duration.ofMinutes(1));
        } else {
            int i = Integer.parseInt(count);
            if (i > LIMIT_TIMES_IN_A_MINUTE)
                throw new CustomException("请求次数过多，请稍后再试");
            stringRedisTemplate.opsForValue().set(redisLimitKey, String.valueOf(i + 1), Duration.ofMinutes(1));
        }

        stringRedisTemplate.expire(redisKey, SIGNIN_DURATION);
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));
        BaseContext.setCurrentIUserDetails(userDetails);
        filterChain.doFilter(request, response);
    }
}
