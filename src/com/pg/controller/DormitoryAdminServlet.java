package com.pg.controller;

import com.pg.entity.DormitoryAdmin;
import com.pg.service.DormitoryAdminService;
import com.pg.service.impl.DormitoryAdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/dormitoryAdmin")
public class DormitoryAdminServlet extends HttpServlet {

    private DormitoryAdminService dormitoryAdminService = new DormitoryAdminServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String method = req.getParameter("method");
        switch (method){
            case "list":
                req.setAttribute("list",dormitoryAdminService.list());
                req.getRequestDispatcher("adminmanager.jsp").forward(req,resp);
                break;
            case "search":
                String key = req.getParameter("key");
                String value = req.getParameter("value");
                req.setAttribute("list",dormitoryAdminService.search(key,value));
                req.getRequestDispatcher("adminmanager.jsp").forward(req,resp);
                break;
            case "save":
                String username = req.getParameter("username");
                String password = req.getParameter("password");
                String name = req.getParameter("name");
                String gender = req.getParameter("gender");
                String telephone = req.getParameter("telephone");
                DormitoryAdmin dormitoryAdmin = new DormitoryAdmin(username,password,name,gender,telephone);
                dormitoryAdminService.save(dormitoryAdmin);
                resp.sendRedirect("/dormitoryAdmin?method=list");
                break;
            case "update":
                String idStr = req.getParameter("id");
                Integer id = Integer.parseInt(idStr);
                username = req.getParameter("username");
                password = req.getParameter("password");
                name = req.getParameter("name");
                gender = req.getParameter("gender");
                telephone = req.getParameter("telephone");
                DormitoryAdmin dormitoryAdmin2 = new DormitoryAdmin(id,username,password,name,gender,telephone);
                dormitoryAdminService.update(dormitoryAdmin2);
                resp.sendRedirect("/dormitoryAdmin?method=list");
                break;
            case "delete":
                idStr = req.getParameter("id");
                id = Integer.parseInt(idStr);
                dormitoryAdminService.delete(id);
                resp.sendRedirect("/dormitoryAdmin?method=list");
                break;
        }
    }
}
