package com.github.nechai.aeroflot.web.servlet;


import com.github.nechai.aeroflot.model.Role;
import com.github.nechai.aeroflot.model.User;

import com.github.nechai.aeroflot.service.impl.UserService;
import com.github.nechai.aeroflot.web.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/Login")
public class Login extends HttpServlet {
    private UserService userService = (UserService) UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) {
        Object authUser = rq.getSession().getAttribute("authUser");
        if (authUser == null) {
            try {
                rs.sendRedirect(rq.getContextPath() + "/registration.jsp");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            rs.sendRedirect(rq.getContextPath() + "/");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) {
        String login = rq.getParameter("login");
        String password = rq.getParameter("password");
        User user = userService.login(login, password);
        if (user == null) {
            rq.setAttribute("error", "login or password invalid");
            WebUtils.forword("login.jsp", rq, rs);
        }
        rq.setAttribute("user", user);
        rq.getSession().setAttribute("authUser", user);
        if (user.getRole() == Role.ADMIN) {
            WebUtils.forword("/admin", rq, rs);
        }
        if (user.getRole() == Role.DISPATCHER) {
            WebUtils.forword("/airport", rq, rs);
        }
        if (user.getRole() == Role.USER) {
            WebUtils.forword("registryticket.jsp", rq, rs);
        }
    }
} 