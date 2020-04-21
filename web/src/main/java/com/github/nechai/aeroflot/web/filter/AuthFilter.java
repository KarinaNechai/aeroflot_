package com.github.nechai.aeroflot.web.filter;

import com.github.nechai.aeroflot.model.Role;
import com.github.nechai.aeroflot.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "Filter2",
     servletNames = {"/airport","/flights","/planes","/workers","/Admin"})
public class AuthFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        User authUser = (User)request.getSession().getAttribute("authUser");
        if(authUser == null){
            logger.warn("The user doesn't work without authorization");
            response.sendRedirect("login.jsp");
        }
        else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

}
