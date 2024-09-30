package ra.ontap;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Todo", value = "/todo")
public class TodoServlet extends HttpServlet {
    private static List<Todo> list = new ArrayList<>();

    static {
        list.add(new Todo(1, "ĐI học", true));
        list.add(new Todo(2, "ĐI chơi", true));
        list.add(new Todo(3, "ĐI ngủ", false));
    }

    private Todo getById(int id){
        return list.stream().filter(t->t.getId()==id).findFirst().orElse(null);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // lay ra tham so
        String action = request.getParameter("action"); // GETALL
        if (action != null) {
            switch (action) {
                case "GETALL":
                    request.setAttribute("todos", list);
                    request.getRequestDispatcher("/views/list-todo.jsp").forward(request, response);
                    break;
                case "DELETE":
                    // lay ra id
                    int idDelete = Integer.parseInt(request.getParameter("id"));
                    list.remove(getById(idDelete));
                   // taoj 1  request theo GET vaf action = GETALL
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
                    Todo todoEdit = getById(idEdit);
                    request.setAttribute("todo", todoEdit);
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
                    int id = Integer.parseInt(request.getParameter("id"));
                    String content = request.getParameter("content");
                    Todo todo = new Todo(id,content,false);
                    list.add(todo);
                    response.sendRedirect("/todo?action=GETALL");
                    break;

                case "UPDATE":
                    int idEdit = Integer.parseInt(request.getParameter("id"));
                    String contentUpdate = request.getParameter("content");
                    boolean statusUpdate = Boolean.parseBoolean(request.getParameter("status"));
                    Todo todoUp = getById(idEdit);
                    todoUp.setContent(contentUpdate);
                    todoUp.setStatus(statusUpdate);
                    response.sendRedirect("/todo?action=GETALL");
                    break;
            }
        }
    }
}