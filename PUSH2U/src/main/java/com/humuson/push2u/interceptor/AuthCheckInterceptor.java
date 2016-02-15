package com.humuson.push2u.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthCheckInterceptor extends HandlerInterceptorAdapter {

	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            if(request.getSession().getAttribute("userLoginInfo") == null ){
            	
                    response.sendRedirect("/"); 
                    
                    return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
