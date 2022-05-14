package com.pg.controller;

import com.pg.entity.Building;
import com.pg.service.BuildingService;
import com.pg.service.DormitoryAdminService;
import com.pg.service.impl.BuildingServiceImpl;
import com.pg.service.impl.DormitoryAdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/building")
public class BuildingServlet extends HttpServlet {

    private BuildingService buildingService = new BuildingServiceImpl();
    private DormitoryAdminService dormitoryAdminService = new DormitoryAdminServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String method = req.getParameter("method");
        switch (method){
            case "list":
                req.setAttribute("list", buildingService.list());
                req.setAttribute("adminList", dormitoryAdminService.list());
                req.getRequestDispatcher("buildingmanager.jsp").forward(req,resp);
                break;
            case "search":
                String key = req.getParameter("key");
                String value = req.getParameter("value");
                req.setAttribute("list", buildingService.search(key,value));
                req.setAttribute("adminList", dormitoryAdminService.list());
                req.getRequestDispatcher("buildingmanager.jsp").forward(req,resp);
                break;
            case "save":
                String name = req.getParameter("name");
                String introduction = req.getParameter("introduction");
                String adminIdStr = req.getParameter("adminId");
                Integer adminId = Integer.parseInt(adminIdStr);
                buildingService.save(new Building(name,introduction,adminId));
                resp.sendRedirect("/building?method=list");
                break;
            case "update":
                String idStr = req.getParameter("id");
                Integer id = Integer.parseInt(idStr);
                name = req.getParameter("name");
                introduction = req.getParameter("introduction");
                adminIdStr = req.getParameter("adminId");
                adminId = Integer.parseInt(adminIdStr);
                buildingService.update(new Building(id,name,introduction,adminId));
                resp.sendRedirect("/building?method=list");
                break;
            case "delete":
                idStr = req.getParameter("id");
                id = Integer.parseInt(idStr);
                buildingService.delete(id);
                resp.sendRedirect("/building?method=list");
                break;
        }
    }
}
