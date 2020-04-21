package com.github.nechai.aeroflot.web.servlet;

import com.github.nechai.aeroflot.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
    public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) {
        User authUser = (User) rq.getSession().getAttribute("authUser");
        rq.getSession().setAttribute("authUser", null);
        rq.setAttribute("users", null);
         try {
                rq.getRequestDispatcher("login.jsp").forward(rq, rs);
              } catch (IOException | ServletException e) {
                throw new RuntimeException(e);
            }
        }

    }
