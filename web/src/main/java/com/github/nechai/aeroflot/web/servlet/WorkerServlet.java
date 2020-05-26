package com.github.nechai.aeroflot.web.servlet;

import com.github.nechai.aeroflot.model.Page;
import com.github.nechai.aeroflot.model.Profession;
import com.github.nechai.aeroflot.model.User;
import com.github.nechai.aeroflot.model.Worker;
import com.github.nechai.aeroflot.service.impl.WorkerService;
import com.github.nechai.aeroflot.web.WebUtils;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/workers")
public class WorkerServlet extends HttpServlet {
    private WorkerService workerService = (WorkerService)  WorkerService.getInstance();
    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs){
        User authUser = (User)rq.getSession().getAttribute("authUser");
        int pageN = 1;
        if (rq.getParameter("page") != null) {
            pageN = Integer.parseInt(rq.getParameter("page"));
        }
        getListWorker(rq,rs,pageN);
    }

    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) {
        User authUser = (User) rq.getSession().getAttribute("authUser");
        int pageN = 1;
        if (rq.getParameter("pageN") != null) {
            pageN = Integer.parseInt(rq.getParameter("pageN"));
        }
        if (rq.getParameter("Add")!=null)
        {
            WebUtils.forword("addWorker.jsp",rq,rs);
        }
        if (rq.getParameter("Update")!=null)
        {
            int workerId=Integer.parseInt(rq.getParameter("Update"));
            Worker workerUp=workerService.getWorkerById(workerId);
            rq.setAttribute("workerUp", workerUp);
            WebUtils.forword("addWorker.jsp",rq,rs);
        }
        if (rq.getParameter("Delete")!=null)
        {
            int workerId=Integer.parseInt(rq.getParameter("Delete"));
            workerService.deleteWorker( workerId);
        }

        if (rq.getParameter("UpdateWorker")!=null)
        {
            int workerId=Integer.parseInt(rq.getParameter("UpdateWorker"));
            Worker worker=new Worker (
                    workerId,
                    rq.getParameter("UpdateWorkerSurName"),
                    rq.getParameter("UpdateWorkerFirstName"),
                    rq.getParameter("UpdateWorkerPatronomic"),
                    Profession.valueOf(rq.getParameter("UpdateWorkerProfession")),
                    true);
            workerService.updateWorker(worker);
            rq.setAttribute("workerUp", null);
        }
        if (rq.getParameter("NewWorker")!=null)
        {
            Worker worker=new Worker (
                    rq.getParameter("NewWorkerSurName"),

                    rq.getParameter("NewWorkerFirstName"),
                    rq.getParameter("NewWorkerPatronomic"),
                    Profession.valueOf(rq.getParameter("NewWorkerProfession")));
            workerService.addWorker(worker);
            rq.setAttribute("NewWorker", null);
        }
        getListWorker(rq,rs,pageN);
    }
    protected void getListWorker(HttpServletRequest rq, HttpServletResponse rs, int pageN){
        int noOfRecords = workerService.getCountOfWorkers();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / Page.RECORD_NUMBER);
        rq.setAttribute("noOfPages", noOfPages);
        rq.setAttribute("currentPage", pageN);
        List <Worker> listWorkers = workerService.getWorkersOfSystem(new Page(pageN));
        rq.setAttribute("listWorkers", listWorkers);
        WebUtils.forword("workers.jsp",rq,rs);
    }
}
