package com.pg.controller;

import com.pg.entity.*;
import com.pg.service.AbsentService;
import com.pg.service.BuildingService;
import com.pg.service.DormitoryService;
import com.pg.service.StudentService;
import com.pg.service.impl.AbsentServiceImpl;
import com.pg.service.impl.BuildingServiceImpl;
import com.pg.service.impl.DormitoryServiceImpl;
import com.pg.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/absent")
public class AbsentServlet extends HttpServlet {

    private BuildingService buildingService = new BuildingServiceImpl();
    private DormitoryService dormitoryService = new DormitoryServiceImpl();
    private StudentService studentService = new StudentServiceImpl();
    private AbsentService absentService = new AbsentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String method = req.getParameter("method");
        switch (method){
            case "init":
                List<Building> buildingList = buildingService.list();
                List<Dormitory> dormitoryList = dormitoryService.findByBuildingId(buildingList.get(0).getId());
                List<Student> studentList = studentService.findByDormitoryId(dormitoryList.get(0).getId());
                req.setAttribute("buildingList", buildingList);
                req.setAttribute("dormitoryList", dormitoryList);
                req.setAttribute("studentList", studentList);
                req.getRequestDispatcher("absentregister.jsp").forward(req,resp);
                break;
            case "save":
                String buildingIdStr = req.getParameter("buildingId");
                Integer buildingId = Integer.parseInt(buildingIdStr);
                String dormitoryIdStr = req.getParameter("dormitoryId");
                Integer dormitoryId = Integer.parseInt(dormitoryIdStr);
                String studentIdStr = req.getParameter("studentId");
                Integer studentId = Integer.parseInt(studentIdStr);
                String reason = req.getParameter("reason");
                String date = req.getParameter("date");
                HttpSession session = req.getSession();
                DormitoryAdmin dormitoryAdmin = (DormitoryAdmin) session.getAttribute("dormitoryAdmin");
                absentService.save(new Absent(buildingId,dormitoryId,studentId,dormitoryAdmin.getId(), date,reason));
                resp.sendRedirect("/absent?method=init");
                break;
            case "list":
                req.setAttribute("list", absentService.list());
                req.getRequestDispatcher("absentrecord.jsp").forward(req,resp);
                break;
            case "search":
                String key = req.getParameter("key");
                String value = req.getParameter("value");
                req.setAttribute("list", absentService.search(key,value ));
                req.getRequestDispatcher("absentrecord.jsp").forward(req,resp);
                break;
        }
    }
}
