package com.pg.controller;

import com.pg.entity.Moveout;
import com.pg.service.MoveoutServie;
import com.pg.service.StudentService;
import com.pg.service.impl.MoveoutServieImpl;
import com.pg.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/moveout")
public class MoveoutServlet extends HttpServlet {

    private StudentService studentService = new StudentServiceImpl();
    private MoveoutServie moveoutServie = new MoveoutServieImpl();

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
                req.setAttribute("list", studentService.moveoutList());
                req.getRequestDispatcher("moveoutregister.jsp").forward(req,resp);
                break;
            case "moveout":
                String studentIdStr = req.getParameter("studentId");
                Integer studentId = Integer.parseInt(studentIdStr);
                String dormitoryIdStr = req.getParameter("dormitoryId");
                Integer dormitoryId = Integer.parseInt(dormitoryIdStr);
                String reason = req.getParameter("reason");
                moveoutServie.save(new Moveout(studentId,dormitoryId,reason));
                resp.sendRedirect("/moveout?method=list");
                break;
            case "search":
                String key = req.getParameter("key");
                String value = req.getParameter("value");
                req.setAttribute("list",studentService.searchForMoveout(key,value));
                req.getRequestDispatcher("moveoutregister.jsp").forward(req,resp);
                break;
            case "record":
                req.setAttribute("list", moveoutServie.list());
                req.getRequestDispatcher("moveoutrecord.jsp").forward(req,resp);
                break;
            case "recordSearch":
                key = req.getParameter("key");
                value = req.getParameter("value");
                req.setAttribute("list", moveoutServie.search(key,value ));
                req.getRequestDispatcher("moveoutrecord.jsp").forward(req,resp);
                break;
        }
    }
}
