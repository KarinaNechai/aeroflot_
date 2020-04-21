package com.github.nechai.aeroflot.web.servlet;

import com.github.nechai.aeroflot.model.Airport;
import com.github.nechai.aeroflot.model.User;
import com.github.nechai.aeroflot.service.impl.AirportService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/airport")
public class AirportServlet extends HttpServlet {
    private AirportService airportService = ( AirportService)  AirportService.getInstance();
    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs){
        User authUser = (User)rq.getSession().getAttribute("authUser");
        getListAirport(rq,rs);
    }

    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs)  {
        User authUser = (User)rq.getSession().getAttribute("authUser");

       if (rq.getParameter("Delete")!=null)
        {
            int airportId=Integer.parseInt(rq.getParameter("Delete"));
            airportService.deleteAirport(airportId);
            getListAirport(rq,rs);
        }
       if (rq.getParameter("Update")!=null)
        {
            try{
            int airportId=Integer.parseInt(rq.getParameter("Update"));
           Airport airportUp=airportService.getAirportById(airportId);
            rq.setAttribute("airportUp", airportUp);
            rq.getRequestDispatcher("addairport.jsp").forward(rq,rs);
        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }}
        if (rq.getParameter("UpdateAirport")!=null)
        {
          int airportId=Integer.parseInt(rq.getParameter("UpdateAirport"));
            Airport airport=new Airport(airportId,rq.getParameter("UpdateAirportName"));
            airportService.updateAirport(airport);
            rq.setAttribute("airportUp", null);
            getListAirport(rq,rs);
        }
        if (rq.getParameter("NewAirport")!=null)
        {
            Airport airport=new Airport(rq.getParameter("NewAirportName"));
            airportService.addAirport(airport);
            rq.setAttribute("NewAirportName", null);
            getListAirport(rq,rs);
        }
        if (rq.getParameter("Add")!=null)
        {
            try {
            rq.getRequestDispatcher("addairport.jsp").forward(rq,rs);
            } catch (IOException | ServletException e) {
                throw new RuntimeException(e);
            }
        }
    }
    protected void getListAirport(HttpServletRequest rq, HttpServletResponse rs){
        try {
            List <Airport> listAirports = airportService.getListAirport();
            rq.setAttribute("listAirports", listAirports);
            rq.getRequestDispatcher("airport.jsp").forward(rq,rs);
        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }
    }

}
