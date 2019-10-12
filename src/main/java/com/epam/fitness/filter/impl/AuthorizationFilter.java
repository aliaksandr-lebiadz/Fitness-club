package com.epam.fitness.filter.impl;

import com.epam.fitness.entity.user.User;
import com.epam.fitness.filter.AbstractFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "authorizationFilter",
        urlPatterns = {"/home", "/getMembership", "/orders", "/assignments", "/trainerClients", "/users"},
        initParams = { @WebInitParam(name = "LOGIN", value = "/login")})
public class AuthorizationFilter extends AbstractFilter {

    private static final String LOGIN_PARAMETER = "LOGIN";

    private String loginPage;

    @Override
    public void init(FilterConfig filterConfig){
        loginPage = filterConfig.getInitParameter(LOGIN_PARAMETER);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        User user = getUser(request);
        if(user == null){
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + loginPage);
        }
        chain.doFilter(servletRequest, servletResponse);
    }
}