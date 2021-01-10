package ua.com.controller;

import ua.com.exceptions.IncorrectInputException;
import ua.com.model.Department;
import ua.com.model.Worker;
import ua.com.services.DepartmentService;
import ua.com.services.WorkerService;
import ua.com.services.impl.DepartmentServiceImpl;
import ua.com.services.impl.WorkerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(urlPatterns = "/MyServlet")
public class MyServlet extends HttpServlet {
    private WorkerService workerService = new WorkerServiceImpl();
    private DepartmentService departmentService = new DepartmentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("delete") != null) {
            deleteWorker(req, resp);
        }


        if (req.getParameter("update") != null) {
            req.getRequestDispatcher("update.jsp").forward(req, resp);
        }

        if (req.getParameter("jspname").equals("update.jsp")) {
            System.out.println("OKKKKK");
            try {
                Worker worker = createWorker(req);
                int id = Integer.parseInt(req.getParameter("departmentId"));
                workerService.updateWorker(id, worker);
                resp.getWriter().println("User updated");
            } catch (ParseException | IncorrectInputException | SQLIntegrityConstraintViolationException e) {
                req.setAttribute("msg", e.getLocalizedMessage());
                req.getRequestDispatcher("exception.jsp").forward(req, resp);
            }
        }
    }

    private void deleteWorker(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("workerId"));
        String login = workerService.selectWorkerById(id).getLogin();
        workerService.deleteWorker(id);
        resp.sendRedirect("successDelete.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("jspname").equals("addWorker.jsp")) {
            writeWorkerToBase(req, resp);
        }

        if (req.getParameter("jspname").equals("addDepartment.jsp")) {
            Department department = createDepartment(req);
            try {
                departmentService.addDepartment(department);
            } catch (SQLIntegrityConstraintViolationException | IncorrectInputException e) {
                req.setAttribute("msg", e.getLocalizedMessage());
                req.getRequestDispatcher("exception.jsp").forward(req, resp);
            }
        }
    }

    private void writeWorkerToBase(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Worker worker = null;
        try {
            worker = createWorker(req);
            workerService.addWorker(worker);
        } catch (SQLIntegrityConstraintViolationException | IncorrectInputException | NumberFormatException | NullPointerException | ParseException e) {
            req.setAttribute("msg", e.getLocalizedMessage());
            req.getRequestDispatcher("exception.jsp").forward(req, resp);
        }
    }

    private Worker createWorker(HttpServletRequest request) throws NumberFormatException, ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Worker worker = new Worker();
        worker.setLogin(request.getParameter("login"));
        worker.setDepartmentId(Integer.parseInt(request.getParameter("departmentId")));
        worker.setFirstName(request.getParameter("firstName"));
        worker.setLastName(request.getParameter("lastName"));
        worker.setEmail(request.getParameter("email"));
        worker.setBirthdayDate(simpleDateFormat.parse(request.getParameter("birthdayDate")));
        return worker;
    }

    private Department createDepartment(HttpServletRequest request) {
        Department department = new Department();
        department.setName(request.getParameter("name"));
        department.setAddress(request.getParameter("address"));
        return department;
    }
}
