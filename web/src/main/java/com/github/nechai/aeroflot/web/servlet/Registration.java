package com.github.nechai.aeroflot.web.servlet;

import com.github.nechai.aeroflot.model.Role;
import com.github.nechai.aeroflot.model.User;
import com.github.nechai.aeroflot.service.impl.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration")
public class Registration extends HttpServlet {
    private UserService userService = (UserService) UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        Object authUser = rq.getSession().getAttribute("authUser");
        if (authUser == null) {
            try {
                rs.sendRedirect(rq.getContextPath() + "/registration.jsp");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            rq.getRequestDispatcher("check.jsp").forward(rq,rs);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) {
        if (rq.getParameter("Save")!=null)
        {
            String firstName = rq.getParameter("firstName");
            String lastName = rq.getParameter("lastName");
            String login = rq.getParameter("login");
            String password = rq.getParameter("password");
            String email = rq.getParameter("email");
            String telephone = rq.getParameter("telephone");
            User user=new User(firstName,lastName,telephone,email,login,password, Role.USER);
            boolean isAddUser= userService.addUser(user);
            if (isAddUser) {
                try {
                    rq.setAttribute("userSave",user);
                    rq.getRequestDispatcher("successRegistration.jsp").forward(rq,rs);
                } catch (IOException | ServletException e) {
                    throw new RuntimeException(e);
                }
            }
            if (!isAddUser) {
                try {
                    rs.sendRedirect(rq.getContextPath() +"/login");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        if (rq.getParameter("Cancel")!=null){
            try {
                rs.sendRedirect(rq.getContextPath() +"/login");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }
}