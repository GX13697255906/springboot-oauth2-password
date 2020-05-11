package com.gx.config.security;

import com.alibaba.fastjson.JSON;
import com.gx.util.ResultEnum;
import com.gx.util.ResultVO;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: guoxun
 * @date: 2018/10/15 16:12
 * @description: 用户登录成功时返回给前端的数据
 */

@Component
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        SelfUserDetails userDetails = (SelfUserDetails) authentication.getPrincipal();
        String jwtToken = JwtTokenUtil.generateToken(userDetails.getUsername(), 60*60*3);
        response.getWriter().write(JSON.toJSONString(ResultVO.result(ResultEnum.USER_LOGIN_SUCCESS,jwtToken,true)));
    }
}
