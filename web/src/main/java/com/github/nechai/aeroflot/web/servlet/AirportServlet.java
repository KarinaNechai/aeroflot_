package com.github.nechai.aeroflot.web.servlet;

import com.github.nechai.aeroflot.model.Airport;
import com.github.nechai.aeroflot.model.Page;
import com.github.nechai.aeroflot.model.User;
import com.github.nechai.aeroflot.service.impl.AirportService;
import com.github.nechai.aeroflot.web.WebUtils;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/airport")
public class AirportServlet extends HttpServlet {
    private AirportService airportService = (AirportService) AirportService.getInstance();

    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) {
        User authUser = (User) rq.getSession().getAttribute("authUser");
        int pageN = 1;
        if (rq.getParameter("page") != null) {
            pageN = Integer.parseInt(rq.getParameter("page"));
        }
        getListAirport(rq, rs, pageN);
    }

    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) {
        User authUser = (User) rq.getSession().getAttribute("authUser");
        int pageN = 1;
        if (rq.getParameter("pageN") != null) {
            pageN = Integer.parseInt(rq.getParameter("pageN"));
        }
        if (rq.getParameter("Delete") != null) {
            int airportId = Integer.parseInt(rq.getParameter("Delete"));
            airportService.deleteAirport(airportId);
        }
        if (rq.getParameter("Update") != null) {
            int airportId = Integer.parseInt(rq.getParameter("Update"));
            Airport airportUp = airportService.getAirportById(airportId);
            rq.setAttribute("airportUp", airportUp);
            WebUtils.forword("addairport.jsp", rq, rs);
        }
        if (rq.getParameter("UpdateAirport") != null) {
            int airportId = Integer.parseInt(rq.getParameter("UpdateAirport"));
            Airport airport = new Airport(airportId, rq.getParameter("UpdateAirportName"));
            airportService.updateAirport(airport);
            rq.setAttribute("airportUp", null);
        }
        if (rq.getParameter("NewAirport") != null) {
            Airport airport = new Airport(rq.getParameter("NewAirportName"));
            airportService.addAirport(airport);
            rq.setAttribute("NewAirportName", null);
        }
        if (rq.getParameter("Add") != null) {
            WebUtils.forword("addairport.jsp", rq, rs);
        }
        getListAirport(rq, rs, pageN);
    }

    protected void getListAirport(HttpServletRequest rq, HttpServletResponse rs, int pageN) {
        int noOfRecords = airportService.getCountOfAirports();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / Page.RECORD_NUMBER);
        rq.setAttribute("noOfPages", noOfPages);
        rq.setAttribute("currentPage", pageN);
        List<Airport> listAirports = airportService.getListAirport(new Page(pageN));
        rq.setAttribute("listAirports", listAirports);
        WebUtils.forword("airport.jsp", rq, rs);
    }
}
