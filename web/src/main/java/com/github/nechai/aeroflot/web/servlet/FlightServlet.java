package com.github.nechai.aeroflot.web.servlet;

import com.github.nechai.aeroflot.model.*;
import com.github.nechai.aeroflot.service.impl.AirportService;
import com.github.nechai.aeroflot.service.impl.CrewService;
import com.github.nechai.aeroflot.service.impl.FlightService;
import com.github.nechai.aeroflot.service.impl.WorkerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/flights")
public class FlightServlet extends HttpServlet {
    private FlightService flightService = (FlightService) FlightService.getInstance();
    private WorkerService workerService = (WorkerService) WorkerService.getInstance();
    private AirportService airportService = (AirportService) AirportService.getInstance();
    private CrewService crewService = (CrewService) CrewService.getInstance();
    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) {
        User authUser = (User) rq.getSession().getAttribute("authUser");
        getListFlight(rq, rs);

    }

    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        User authUser = (User) rq.getSession().getAttribute("authUser");
        if (rq.getParameter("AddCrew") != null) {
            int flightId = Integer.parseInt(rq.getParameter("AddCrew"));
            Crew crew=crewService.getCrewByFlightId(flightId);
            if (crew.getCrewId()!=-1)
            {
                List<Worker> listPilot=crew.getListWorkerByProfession(Profession.PILOT);
                List<Worker> listStewardess=crew.getListWorkerByProfession(Profession.STEWARDESS);
                List<Worker> listNavigator=crew.getListWorkerByProfession(Profession.NAVIGATOR);
                List<Worker> listRadioman=crew.getListWorkerByProfession(Profession.RADIOMAN);
                rq.setAttribute("listPilot", listPilot);
                rq.setAttribute("listStewardess", listStewardess);
                rq.setAttribute("listNavigator", listNavigator);
                rq.setAttribute("listRadioman", listRadioman);

                rq.setAttribute("crewView", crew);
            }
            else {
                Crew crewNew = new Crew(flightId);
                rq.setAttribute("NewCrewIsAdd", crew);
                List<Worker> listPilot = workerService.getWorkersByProfession(Profession.PILOT);
                List<Worker> listStewardess = workerService.getWorkersByProfession(Profession.STEWARDESS);
                List<Worker> listNavigator = workerService.getWorkersByProfession(Profession.NAVIGATOR);
                List<Worker> listRadioman = workerService.getWorkersByProfession(Profession.RADIOMAN);
                rq.setAttribute("listPilot", listPilot);
                rq.setAttribute("listStewardess", listStewardess);
                rq.setAttribute("listNavigator", listNavigator);
                rq.setAttribute("listRadioman", listRadioman);
            }
                rq.getRequestDispatcher("addCrewNew.jsp").forward(rq, rs);
        }
        if (rq.getParameter("AddFlight") != null) {
            List<Airport> listAirport = airportService.getListAirport();
            rq.setAttribute("listAirport", listAirport);
            rq.getRequestDispatcher("addFlight.jsp").forward(rq, rs);
        }
        if (rq.getParameter("NewFlightAdd") != null) {
            int a1 = Integer.parseInt(rq.getParameter("AirportFrom"));
            int a2 = Integer.parseInt(rq.getParameter("AirportTo"));
      /*      DateFormat dateFormat = DateFormat.getDateTimeInstance();
            String dateString = "19.11.2004 13:00:00";

            Date date1 = dateFormat.parse(date);
            System.out.println(date);*/
            String date = rq.getParameter("Date");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date1 = new Date();
            try {
                date1 = format.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();

                flightService.insert(a1, a2, date1);
                getListFlight(rq, rs);
            }
        }
    }
    public void getListFlight(HttpServletRequest rq, HttpServletResponse rs) {
        try {
            List<Flight> listFlight = flightService.getListFlight();
            rq.setAttribute("listFlight", listFlight);
            rq.getRequestDispatcher("flights.jsp").forward(rq, rs);
        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }
    }
}

