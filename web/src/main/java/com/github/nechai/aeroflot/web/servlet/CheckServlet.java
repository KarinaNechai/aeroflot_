package com.github.nechai.aeroflot.web.servlet;

import com.github.nechai.aeroflot.model.Check;
import com.github.nechai.aeroflot.model.TypeOfCheck;
import com.github.nechai.aeroflot.model.User;
import com.github.nechai.aeroflot.service.impl.CheckService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@WebServlet("/check")
public class CheckServlet extends HttpServlet {
    private CheckService checkService = (CheckService) CheckService.getInstance();

    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs){

    }

    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) {
        User authUser = (User)rq.getSession().getAttribute("authUser");
        if (rq.getParameter("Logout")!=null)
        {
            rq.getSession().setAttribute("authUser", null);
            rq.setAttribute("users", null);
            try {
                rq.getRequestDispatcher("login.jsp").forward(rq, rs);
            } catch (IOException | ServletException e) {
                throw new RuntimeException(e);
            }
        }
        if (rq.getParameter("AddCheck")!=null){
            TypeOfCheck type=TypeOfCheck.valueOf(rq.getParameter("rdo"));
            String date = rq.getParameter("date");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = new Date();
            try {
                date1 = format.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            float sum = Float.parseFloat(rq.getParameter("sum"));
            String text = rq.getParameter("text");
            Check check=new Check(authUser.getLogin(),type,date1,sum,text);
            check=checkService.insertCheck(check);
            if (check!=null){
                rq.setAttribute("message", "check N"+check.getNumber()+" add");
            }
            else {
                rq.setAttribute("message", "check didn't add");
            }
            rq.setAttribute("date", LocalDateTime.now());
            rq.setAttribute("text", "");
            rq.setAttribute("sum", "0");

        }
        if (rq.getParameter("Delete")!=null)
        {
            int checkN=Integer.parseInt(rq.getParameter("Delete"));
            checkService.deleteCheck(checkN);
        }
        try {
                List<Check> listCheck = checkService.getChecks(authUser.getLogin());
                rq.setAttribute("listCheck", listCheck);
                float totalRevenue= checkService.totalByTypeOfCheck(listCheck,TypeOfCheck.REVENUE);
                float totalExpences= checkService.totalByTypeOfCheck(listCheck,TypeOfCheck.EXPENCES);
                rq.setAttribute("totalRevenue", totalRevenue);
                rq.setAttribute("totalExpences", totalExpences);
                rq.getRequestDispatcher("check.jsp").forward(rq,rs);
            } catch (IOException | ServletException e) {
                throw new RuntimeException(e);
    }

    }
}
