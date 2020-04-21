package com.github.nechai.aeroflot.web.servlet;

import com.github.nechai.aeroflot.model.*;
import com.github.nechai.aeroflot.service.impl.CrewService;
import com.github.nechai.aeroflot.service.impl.FlightService;
import com.github.nechai.aeroflot.service.impl.WorkerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/crew")
public class CrewServlet extends HttpServlet {
    private CrewService crewService = (CrewService) CrewService.getInstance();
    private WorkerService workerService = (WorkerService) WorkerService.getInstance();
    private FlightService flightService = (FlightService) FlightService.getInstance();
    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs){
        User authUser = (User)rq.getSession().getAttribute("authUser");
        try {
            if (rq.getParameter("AddCrew")!=null){
            int flightId=Integer.parseInt(rq.getParameter("flightId"));
            List<Worker> listPilot= workerService.getWorkersByProfession(Profession.PILOT);
            //          List<Worker> listNavigator= workerService.getWorkersByProfession(Profession.NAVIGATOR);
//            List<Worker> listStewardess= workerService.getWorkersByProfession(Profession.STEWARDESS);
            //          List<Worker> listRadioman= workerService.getWorkersByProfession(Profession.RADIOMAN);
            rq.setAttribute("listPilot", listPilot);
//            rq.setAttribute("listNavigator", listNavigator);
            //         rq.setAttribute("listStewardess", listStewardess);
            //         rq.setAttribute("listRadioman", listRadioman);
            rq.getRequestDispatcher("addCrewNew.jsp").forward(rq,rs);
        }
            Crew crewUp=crewService.getCrewByFlightId(1);
          //  crewUp.getListWorker();
            rq.setAttribute("CrewUp", crewUp);
            rq.getRequestDispatcher("addCrew.jsp").forward(rq,rs);
        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        User authUser = (User) rq.getSession().getAttribute("authUser");
        if (rq.getParameter("Logout") != null) {
            rq.getSession().setAttribute("authUser", null);
            rq.setAttribute("users", null);
            try {
                rq.getRequestDispatcher("login.jsp").forward(rq, rs);
            } catch (IOException | ServletException e) {
                throw new RuntimeException(e);
            }
        }
      if (rq.getParameter("Create")!=null){
            Crew crewView=addNewCrew(rq, rs);
            getListFlight(rq,rs);
            rq.getRequestDispatcher("flights.jsp ").forward(rq,rs);
        }
        if (rq.getParameter("Continue")!=null){
            getListFlight(rq,rs);
            rq.getRequestDispatcher("flights.jsp ").forward(rq,rs);
        }
   }
    private Crew addNewCrew (HttpServletRequest rq, HttpServletResponse rs)
    {
        List <Integer> workers=new ArrayList<>();
        if (rq.getParameter("Pilot")!=null){
            String str=rq.getParameter("Pilot");
            if (!str.equals("")) {
                workers.add(Integer.parseInt(rq.getParameter("Pilot")));
            }
        }
        if (rq.getParameter("Radioman")!=null){
            String str=rq.getParameter("Radioman");
            if (!str.equals("")) {
                workers.add(Integer.parseInt(rq.getParameter("Radioman")));
            }
        }
        if (rq.getParameter("Stwerdess")!=null){
            String str=rq.getParameter("Stwerdess");
            if (!str.equals("")){
                workers.add(Integer.parseInt(rq.getParameter("Stwerdess")));
            }
        }
        if (rq.getParameter("Navigator")!=null){
            String str=rq.getParameter("Navigator");
            if (!str.equals("")){
                workers.add(Integer.parseInt(rq.getParameter("Navigator")));
            }
        }
        int flightId=Integer.parseInt(rq.getParameter("Create"));
        return crewService.insert(flightId,workers)?crewService.getCrewByFlightId(flightId):null;
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
