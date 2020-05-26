package com.github.nechai.aeroflot.web.servlet;

import com.github.nechai.aeroflot.model.Page;
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

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private UserService userService = (UserService) UserService.getInstance();

    @Override
    protected void doGet (HttpServletRequest rq, HttpServletResponse rs)  {
        Object authUser = rq.getSession().getAttribute("authUser");
        int pageN = 1;
        if (rq.getParameter("page") != null) {
            pageN = Integer.parseInt(rq.getParameter("page"));
        }
        getListUsers(rq, rs,pageN);
    }

    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        int pageN = 1;
        if (rq.getParameter("pageN") != null) {
            pageN = Integer.parseInt(rq.getParameter("pageN"));
        }
        String userLoginSave = rq.getParameter("Save");
        if (rq.getParameter("Delete")!=null)
        {
            int userId= Integer.parseInt(rq.getParameter("Delete"));
            userService.delete( userId);
        }
        if (rq.getParameter("Update")!=null)
        {
            int userId= Integer.parseInt(rq.getParameter("Update"));
            rq.setAttribute("userUpdate",userService.getUserById(userId));
        }
        if (rq.getParameter("Save")!=null)
        {
            int userId= Integer.parseInt(rq.getParameter("Save"));
            userService.updateUser(userService.getUserById(userId));
        }
        if (rq.getParameter("Logout")!=null)
        {
            rq.getSession().setAttribute("authUser", null);
            rq.setAttribute("users", null);
            WebUtils.forword("login.jsp",rq, rs);
        }
        getListUsers(rq, rs,pageN);
    }
    protected void getListUsers(HttpServletRequest rq, HttpServletResponse rs, int pageN){
        int noOfRecords = userService.getCountOfUsers();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / Page.RECORD_NUMBER);
        rq.setAttribute("noOfPages", noOfPages);
        rq.setAttribute("currentPage", pageN);
        List<User> listUsers = userService.getUsersOfSystem(new Page(pageN));
        rq.setAttribute("users", listUsers);
        WebUtils.forword("admin.jsp",rq, rs);
    }
}

