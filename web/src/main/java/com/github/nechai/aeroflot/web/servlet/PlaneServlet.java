package com.github.nechai.aeroflot.web.servlet;

import com.github.nechai.aeroflot.model.Page;
import com.github.nechai.aeroflot.model.Plane;
import com.github.nechai.aeroflot.model.User;
import com.github.nechai.aeroflot.service.impl.PlaneService;
import com.github.nechai.aeroflot.web.WebUtils;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/planes")
public class PlaneServlet extends HttpServlet {
    private PlaneService planeService = (PlaneService) PlaneService.getInstance();

    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) {
        User authUser = (User) rq.getSession().getAttribute("authUser");
        int pageN = 1;
        if (rq.getParameter("page") != null) {
            pageN = Integer.parseInt(rq.getParameter("page"));
        }
        getListPlanes(rq, rs, pageN);
    }

    protected void doPost(HttpServletRequest rq, HttpServletResponse rs)  {
        User authUser = (User) rq.getSession().getAttribute("authUser");
        int pageN = 1;
        if (rq.getParameter("pageN") != null) {
            pageN = Integer.parseInt(rq.getParameter("pageN"));
        }

        if (rq.getParameter("Add") != null) {
            WebUtils.forword("addPlane.jsp",rq, rs);
        }
        if (rq.getParameter("Update") != null) {
            int planeId = Integer.parseInt(rq.getParameter("Update"));
            Plane planeUp = planeService.getPlaneById(planeId);
            rq.setAttribute("planeUp", planeUp);
            WebUtils.forword("addPlane.jsp",rq, rs);
        }
        if (rq.getParameter("Delete") != null) {
            int planeId = Integer.parseInt(rq.getParameter("Delete"));
            planeService.delete(planeId);
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
        }
        if (rq.getParameter("NewPlane") != null) {
            Plane plane = new Plane(
                    rq.getParameter("NewPlaneName"),
                    Integer.parseInt(rq.getParameter("NewCapasity")),
                    Integer.parseInt(rq.getParameter("NewRange")));
            planeService.addPlane(plane);
            rq.setAttribute("NewPlane", null);
        }
        getListPlanes( rq,rs,pageN);
    }

    protected void getListPlanes(HttpServletRequest rq, HttpServletResponse rs, int pageN) {
        int noOfRecords = planeService.getCountOfPlanes();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / Page.RECORD_NUMBER);
        rq.setAttribute("noOfPages", noOfPages);
        rq.setAttribute("currentPage", pageN);
        List<Plane> listPlanes = planeService.getListPlane(new Page(pageN));
        rq.setAttribute("listPlanes", listPlanes);
        WebUtils.forword("planes.jsp", rq, rs);
    }
}
