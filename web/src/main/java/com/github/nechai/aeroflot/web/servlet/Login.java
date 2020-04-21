package com.github.nechai.aeroflot.web.servlet;

import com.github.nechai.aeroflot.model.Airport;
import com.github.nechai.aeroflot.model.Role;
import com.github.nechai.aeroflot.model.User;
import com.github.nechai.aeroflot.service.impl.AirportService;
import com.github.nechai.aeroflot.service.impl.CheckService;
import com.github.nechai.aeroflot.service.impl.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/Login")
public class Login extends HttpServlet {
    private AirportService airportService = (AirportService) AirportService.getInstance();
    private UserService userService = (UserService) UserService.getInstance();
    private CheckService checkService = (CheckService) CheckService.getInstance();
    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs)  {
        Object authUser = rq.getSession().getAttribute("authUser");
        if (authUser == null) {
            try {
               rs.sendRedirect(rq.getContextPath() + "/registration.jsp");
            }
             catch (IOException e) {
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
           try {
               rq.getRequestDispatcher("login.jsp").forward(rq,rs);
           } catch (ServletException|IOException e) {
               e.printStackTrace();
           }
       }
        rq.setAttribute("user",user);
        rq.getSession().setAttribute("authUser", user);
        if (user.getRole()== Role.ADMIN){
            try {
                List<User> listUsers = userService.getUsersOfSystem();
                rq.setAttribute("users", listUsers);
                rq.getRequestDispatcher("admin.jsp").forward(rq,rs);
            } catch (IOException | ServletException e) {
                throw new RuntimeException(e);
            }
        }
        if (user.getRole()== Role.DISPATCHER){
            try {
                List<Airport> listAirports = airportService.getListAirport();
                rq.setAttribute("listAirports", listAirports);
                rq.getRequestDispatcher("airport.jsp").forward(rq,rs);
            } catch (IOException | ServletException e) {
                throw new RuntimeException(e);
            }
        }
        if (user.getRole()== Role.USER){
            try {
 /*                List<Check> listCheck = checkService.getChecks(user.getLogin());
               rq.setAttribute("listCheck", listCheck);
                float totalRevenue= checkService.totalByTypeOfCheck(listCheck, TypeOfCheck.REVENUE);
                float totalExpences= checkService.totalByTypeOfCheck(listCheck,TypeOfCheck.EXPENCES);
                rq.setAttribute("totalRevenue", totalRevenue);
                rq.setAttribute("totalExpences", totalExpences);*/
                rq.getRequestDispatcher("registryticket.jsp").forward(rq,rs);
             } catch (IOException | ServletException e) {
                throw new RuntimeException(e);
            }
        }
    }
} 