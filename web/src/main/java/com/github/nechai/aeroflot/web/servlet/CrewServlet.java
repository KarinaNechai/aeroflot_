package com.github.nechai.aeroflot.web.servlet;

import com.github.nechai.aeroflot.model.*;
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
    private WorkerService workerService = (WorkerService) WorkerService.getInstance();
    private FlightService flightService = (FlightService) FlightService.getInstance();
    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs){
        User authUser = (User)rq.getSession().getAttribute("authUser");
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
             addWorkersToFlight(rq, rs);
            getListFlight(rq,rs);
            rq.getRequestDispatcher("flights.jsp ").forward(rq,rs);
        }
        if (rq.getParameter("Continue")!=null){
            getListFlight(rq,rs);
            rq.getRequestDispatcher("flights.jsp ").forward(rq,rs);
        }
   }
    private void addWorkersToFlight (HttpServletRequest rq, HttpServletResponse rs)
    {
        List <Worker> workers=new ArrayList<>();
        if (rq.getParameter("Pilot")!=null){
            if (!rq.getParameter("Pilot").equals("")) {
                int id=Integer.parseInt(rq.getParameter("Pilot"));
                workers.add(workerService.getWorkerById(id));
            }
        }
        if (rq.getParameter("Radioman")!=null){
            if (!rq.getParameter("Radioman").equals("")) {
                int id=Integer.parseInt(rq.getParameter("Radioman"));
                workers.add(workerService.getWorkerById(id));
            }
        }
        if (rq.getParameter("Stwerdess")!=null){
            if (!rq.getParameter("Stwerdess").equals("")){
                int id=Integer.parseInt(rq.getParameter("Stwerdess"));
                workers.add(workerService.getWorkerById(id));
            }
        }
        if (rq.getParameter("Navigator")!=null){
           if (!rq.getParameter("Navigator").equals("")){
               int id=Integer.parseInt(rq.getParameter("Navigator"));
               workers.add(workerService.getWorkerById(id));
            }
        }
        int flightId=Integer.parseInt(rq.getParameter("Create"));
         Flight flight=flightService.getFlightById( flightId);
         flight.setListWorker(workers);
        flightService.save(flight);
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
