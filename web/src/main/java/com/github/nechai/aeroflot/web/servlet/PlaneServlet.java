package com.github.nechai.aeroflot.web.servlet;

import com.github.nechai.aeroflot.model.Plane;
import com.github.nechai.aeroflot.model.User;
import com.github.nechai.aeroflot.service.impl.PlaneService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/planes")
public class PlaneServlet extends HttpServlet {
    private PlaneService planeService = (PlaneService) PlaneService.getInstance();

    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) {
        User authUser = (User) rq.getSession().getAttribute("authUser");
        getListPlanes(rq, rs);
    }

    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        User authUser = (User) rq.getSession().getAttribute("authUser");

        if (rq.getParameter("Delete") != null) {
            int planeId = Integer.parseInt(rq.getParameter("Delete"));
            planeService.delete(planeId);
            getListPlanes(rq, rs);
        }
        if (rq.getParameter("Update") != null) {
            int planeId = Integer.parseInt(rq.getParameter("Update"));
            Plane planeUp = planeService.getPlaneById(planeId);
            rq.setAttribute("planeUp", planeUp);
            rq.getRequestDispatcher("addPlane.jsp").forward(rq, rs);
        }
        if (rq.getParameter("UpdatePlane") != null) {
            String str = rq.getParameter("UpdatePlane");
            int planeId = Integer.parseInt(rq.getParameter("UpdatePlane"));
            Plane plane = new Plane(
                    planeId,
                    rq.getParameter("UpdatePlaneName"),
                    Integer.parseInt(rq.getParameter("UpdateCapasity")),
                    Integer.parseInt(rq.getParameter("UpdateRange")),
                    true);
            planeService.update(plane);
            rq.setAttribute("planeUp", null);
            getListPlanes(rq, rs);
        }
        if (rq.getParameter("NewPlane") != null) {
            Plane plane = new Plane(
                    rq.getParameter("NewPlaneName"),
                    Integer.parseInt(rq.getParameter("NewCapasity")),
                    Integer.parseInt(rq.getParameter("NewRange")));
            planeService.addPlane(plane);
            rq.setAttribute("NewPlane", null);
            getListPlanes(rq, rs);
        }
         if (rq.getParameter("Add") != null) {
            rq.getRequestDispatcher("addPlane.jsp").forward(rq, rs);
        }

    }
    protected void getListPlanes(HttpServletRequest rq, HttpServletResponse rs){
        try {
            List<Plane> listPlanes = planeService.getListPlane();
            rq.setAttribute("listPlanes", listPlanes);
            rq.getRequestDispatcher("planes.jsp").forward(rq, rs);
        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }
    }

}
