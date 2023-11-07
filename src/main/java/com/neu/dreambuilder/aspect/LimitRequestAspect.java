package com.neu.dreambuilder.aspect;

import com.neu.dreambuilder.common.annotation.LimitRequest;
import com.neu.dreambuilder.dto.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LimitRequestAspect {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Value("${dream-builder.redis.prefix.limit-ip}")
    private String REDIS_PREFIX_LIMIT_IP;

    @Pointcut("@annotation(limitRequest)")
    public void excludeService(LimitRequest limitRequest) {
    }

    @Around("excludeService(limitRequest)")
    public Object doAround(ProceedingJoinPoint pjp, LimitRequest limitRequest) throws Throwable {

        // 获得request对象
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

        String key = REDIS_PREFIX_LIMIT_IP
                + request.getServletPath().substring(1).replace('/', ':')
                + ":" + request.getRemoteAddr();
        String times = stringRedisTemplate.opsForValue().get(key);
        int t;
        if (times == null) t = 1;
        else {
            t = Integer.parseInt(times) + 1;
            if (t > limitRequest.timesInAUnit()) return Result.error("请求次数过多，请稍后再试");
        }
        stringRedisTemplate.opsForValue().set(key, Integer.toString(t), 1, limitRequest.unit());
        return pjp.proceed();
    }

}