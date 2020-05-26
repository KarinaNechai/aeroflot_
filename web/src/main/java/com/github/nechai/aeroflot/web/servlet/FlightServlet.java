package com.github.nechai.aeroflot.web.servlet;

import com.github.nechai.aeroflot.model.*;
import com.github.nechai.aeroflot.service.impl.AirportService;
import com.github.nechai.aeroflot.service.impl.FlightService;
import com.github.nechai.aeroflot.service.impl.WorkerService;
import com.github.nechai.aeroflot.web.WebUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@WebServlet("/flights")
public class FlightServlet extends HttpServlet {
    private FlightService flightService = (FlightService) FlightService.getInstance();
    private WorkerService workerService = (WorkerService) WorkerService.getInstance();
    private AirportService airportService = (AirportService) AirportService.getInstance();

    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) {
        User authUser = (User) rq.getSession().getAttribute("authUser");
        int pageN = 1;
        if (rq.getParameter("page") != null) {
            pageN = Integer.parseInt(rq.getParameter("page"));
        }
        getListFlight(rq, rs, pageN);
    }

    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) {
        User authUser = (User) rq.getSession().getAttribute("authUser");
        int pageN = 1;
        if (rq.getParameter("pageN") != null) {
            pageN = Integer.parseInt(rq.getParameter("pageN"));
        }
        if (rq.getParameter("AddCrew") != null) {
            int flightId = Integer.parseInt(rq.getParameter("AddCrew"));
            Flight flight = flightService.getFlightById(flightId);
            List<Worker> listWorker = flight.getListWorker();
            if (listWorker!=null) {
                List<Worker> listPilot = listWorker.stream().filter(s -> s.getProfession().equals(Profession.PILOT)).collect(Collectors.toList());
                List<Worker> listStewardess = listWorker.stream().filter(s -> s.getProfession().equals(Profession.STEWARDESS)).collect(Collectors.toList());
                List<Worker> listNavigator = listWorker.stream().filter(s -> s.getProfession().equals(Profession.NAVIGATOR)).collect(Collectors.toList());
                List<Worker> listRadioman = listWorker.stream().filter(s -> s.getProfession().equals(Profession.RADIOMAN)).collect(Collectors.toList());
                rq.setAttribute("listPilot", listPilot);
                rq.setAttribute("listStewardess", listStewardess);
                rq.setAttribute("listNavigator", listNavigator);
                rq.setAttribute("listRadioman", listRadioman);
                rq.setAttribute("flightWorkersView", flight);
            } else {
                rq.setAttribute("flightWorkersAdd", flight);
                List<Worker> listPilot = workerService.getWorkersByProfession(Profession.PILOT);
                List<Worker> listStewardess = workerService.getWorkersByProfession(Profession.STEWARDESS);
                List<Worker> listNavigator = workerService.getWorkersByProfession(Profession.NAVIGATOR);
                List<Worker> listRadioman = workerService.getWorkersByProfession(Profession.RADIOMAN);
                rq.setAttribute("listPilot", listPilot);
                rq.setAttribute("listStewardess", listStewardess);
                rq.setAttribute("listNavigator", listNavigator);
                rq.setAttribute("listRadioman", listRadioman);
            }
            WebUtils.forword("addCrewNew.jsp", rq, rs);
        }
        if (rq.getParameter("AddFlight") != null) {
            List<Airport> listAirport = airportService.getListAirport();
            rq.setAttribute("listAirport", listAirport);
            WebUtils.forword("addFlight.jsp", rq, rs);
        }
        if (rq.getParameter("NewFlightAdd") != null) {
            int airportFromId = Integer.parseInt(rq.getParameter("AirportFrom"));
            int airportToId = Integer.parseInt(rq.getParameter("AirportTo"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm", Locale.getDefault());
            String str = rq.getParameter("Date");
            //      ZonedDateTime d = ZonedDateTime.parse(str)
            LocalDateTime date = LocalDateTime.parse(str, formatter);

            Flight flight = new Flight(airportService.getAirportById(airportFromId), airportService.getAirportById(airportToId), date);
            flightService.save(flight);
            getListFlight(rq, rs, pageN);
        }
      /*  if (rq.getParameter("NewFlightAdd") != null) {
            int a1 = Integer.parseInt(rq.getParameter("AirportFrom"));
            int a2 = Integer.parseInt(rq.getParameter("AirportTo"));
            String date = rq.getParameter("Date");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date1 = new Date();
            try {
                date1 = format.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
                flightService.insert(a1, a2, date1);
            }
        }*/
        getListFlight(rq, rs, pageN);

    }

    public void getListFlight(HttpServletRequest rq, HttpServletResponse rs, int pageN) {
        int noOfRecords = flightService.getCountOfFlights();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / Page.RECORD_NUMBER);
        rq.setAttribute("noOfPages", noOfPages);
        rq.setAttribute("currentPage", pageN);
        List<Flight> listFlight = flightService.getListFlight(new Page(pageN));
        rq.setAttribute("listFlight", listFlight);
        WebUtils.forword("flights.jsp", rq, rs);
    }
}

