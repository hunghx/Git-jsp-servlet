package ra.ontap.controller;

import ra.ontap.service.todo.ITodoService;
import ra.ontap.service.todo.TodoServiceImpl;
import ra.ontap.util.ConnectionDB;
import ra.ontap.model.Todo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Todo", value = "/todo")
public class TodoController extends HttpServlet {
    private ITodoService todoService = new TodoServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // lay ra tham so
        String action = request.getParameter("action"); // GETALL
        if (action != null) {
            switch (action) {
                case "GETALL":
                    request.setAttribute("todos", todoService.findAll() );
                    request.getRequestDispatcher("/views/list-todo.jsp").forward(request, response);
                    break;
                case "DELETE":
                    // lay ra id
                    int idDelete = Integer.parseInt(request.getParameter("id"));
                    //logic xóa
                    todoService.deleteById(idDelete);
                    response.sendRedirect("/todo?action=GETALL");
                    break;
                case "ADD":
                    // dieu huong
                    request.getRequestDispatcher("/views/add-todo.jsp").forward(request,response);
                    break;
                case "IMAGE":
                    request.getRequestDispatcher("/WEB-INF/image.jsp").forward(request,response);
                    break;
                case "EDIT":
                    // đổ ra dữ liệu cũ
                    int idEdit = Integer.parseInt(request.getParameter("id"));
                    request.setAttribute("todo",todoService.findById(idEdit));
                    request.getRequestDispatcher("/views/edit-todo.jsp").forward(request, response);
                    break;

            }
        }

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action"); // GETALL
        if (action != null) {
            switch (action) {
                case "ADD":
                    String content = request.getParameter("content");
                    Todo todo = new Todo(null,content,false);
                    todoService.save(todo);
                    response.sendRedirect("/todo?action=GETALL");
                    break;

                case "UPDATE":
                    int idEdit = Integer.parseInt(request.getParameter("id"));
                    String contentUpdate = request.getParameter("content");
                    boolean statusUpdate = Boolean.parseBoolean(request.getParameter("status"));
                    Todo todoUp = new Todo(idEdit,contentUpdate,statusUpdate);
                    todoService.save(todoUp);
                    response.sendRedirect("/todo?action=GETALL");
                    break;
            }
        }
    }
}