package com.neu.dreambuilder.filter;

import com.alibaba.fastjson.JSON;
import com.neu.dreambuilder.common.utils.BaseContext;
import com.neu.dreambuilder.common.utils.JwtUtil;
import com.neu.dreambuilder.entity.user.IUserDetails;
import com.neu.dreambuilder.exception.bean.CustomException;
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
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
public class JwtAuthTokenFilter extends OncePerRequestFilter {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Value("${dream-builder.sys.sign-in-duration}")
    private Duration SIGNIN_DURATION;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader("token");
        if(!StringUtils.hasText(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        String redisKey = JwtUtil.parseJWT(token).getBody().getSubject();
        IUserDetails userDetails = JSON.parseObject(stringRedisTemplate.opsForValue().get(redisKey), IUserDetails.class);
        if(userDetails == null) {
            log.warn("illegal token");
            filterChain.doFilter(request, response);
            return;
        }

        String redisLimitKey = "limit:" + redisKey;


        //根据id禁止频繁发送请求
        if (Objects.isNull(stringRedisTemplate.opsForHash().entries(redisKey))){
            Map<String,Object> hashMap = new HashMap<>();
            hashMap.put("time", LocalDateTime.now());
            hashMap.put("count",0);
            stringRedisTemplate.opsForHash().putAll(redisKey,hashMap);
        }else{
            Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries(redisKey);
            LocalDateTime lasttime = (LocalDateTime) entries.get("time");
            Duration duration = Duration.between(lasttime, LocalDateTime.now());
            long minutes = duration.toMinutes();
            if (minutes < 1){
                Integer count =(Integer) entries.get("count");
                if (count==99){
                    throw new CustomException("请勿频繁操作");
                }
            }

        }




        stringRedisTemplate.expire(redisKey, SIGNIN_DURATION);
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));
        BaseContext.setCurrentIUserDetails(userDetails);
        filterChain.doFilter(request, response);
    }
}
