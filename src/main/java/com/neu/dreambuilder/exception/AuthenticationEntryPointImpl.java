package com.neu.dreambuilder.exception;

import com.alibaba.fastjson.JSON;
import com.neu.dreambuilder.common.utils.WebUtils;
import com.neu.dreambuilder.dto.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        Result<String> error = Result.error("登录失败请重新登陆！");
        String json = JSON.toJSONString(error);
        WebUtils.renderString(httpServletResponse,json);
    }
}
