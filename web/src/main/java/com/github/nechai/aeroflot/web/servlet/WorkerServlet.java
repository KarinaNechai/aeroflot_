package com.github.nechai.aeroflot.web.servlet;

import com.github.nechai.aeroflot.model.Profession;
import com.github.nechai.aeroflot.model.User;
import com.github.nechai.aeroflot.model.Worker;
import com.github.nechai.aeroflot.service.impl.WorkerService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/workers")
public class WorkerServlet extends HttpServlet {
    private WorkerService workerService = (WorkerService)  WorkerService.getInstance();
    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs){
        User authUser = (User)rq.getSession().getAttribute("authUser");
        getListWorker(rq,rs);
    }

    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        User authUser = (User) rq.getSession().getAttribute("authUser");

        if (rq.getParameter("Delete")!=null)
        {
            int workerId=Integer.parseInt(rq.getParameter("Delete"));
            workerService.deleteWorker( workerId);
            getListWorker(rq,rs);
        }
        if (rq.getParameter("Update")!=null)
        {
            int workerId=Integer.parseInt(rq.getParameter("Update"));
            Worker workerUp=workerService.getWorkerById(workerId);
            rq.setAttribute("workerUp", workerUp);
            rq.getRequestDispatcher("addWorker.jsp").forward(rq,rs);
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
            getListWorker(rq,rs);
        }
        if (rq.getParameter("NewWorker")!=null)
        {
           String str=rq.getParameter("NewWorkerProfession");
            Worker worker=new Worker (
                    rq.getParameter("NewWorkerSurName"),

                    rq.getParameter("NewWorkerFirstName"),
                    rq.getParameter("NewWorkerPatronomic"),
                    Profession.valueOf(rq.getParameter("NewWorkerProfession")));
            workerService.addWorker(worker);
            rq.setAttribute("NewWorker", null);
            getListWorker(rq,rs);
        }
        if (rq.getParameter("Add")!=null)
        {
            rq.getRequestDispatcher("addWorker.jsp").forward(rq,rs);
        }

    }
    protected void getListWorker(HttpServletRequest rq, HttpServletResponse rs){
        try {
            List <Worker> listWorkers = workerService.getWorkersOfSystem();
            rq.setAttribute("listWorkers", listWorkers);
            rq.getRequestDispatcher("workers.jsp").forward(rq,rs);
        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }
    }
}
