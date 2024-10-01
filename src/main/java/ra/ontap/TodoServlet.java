package ra.ontap;

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
public class TodoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // lay ra tham so
        String action = request.getParameter("action"); // GETALL
        if (action != null) {
            switch (action) {
                case "GETALL":
                    request.setAttribute("todos", findAll());
                    request.getRequestDispatcher("/views/list-todo.jsp").forward(request, response);
                    break;
                case "DELETE":
                    // lay ra id
                    int idDelete = Integer.parseInt(request.getParameter("id"));
                    //logic xóa
                    delete(idDelete);
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
                    request.getRequestDispatcher("/views/edit-todo.jsp").forward(request, response);
                    break;

            }
        }

    }

    // lấy danh sach công việc từ CSDL
    private List<Todo> findAll(){
        List<Todo> list = new ArrayList<>();
        //b1 mở kết nối
        Connection conn = ConnectionDB.getConnection();
        // b2 tạo truy vấn
        try {
            CallableStatement call = conn.prepareCall("select * from todo");
            // thực thi câu lệnh
            ResultSet rs = call.executeQuery();
            // xử lí dữ liệu
            while (rs.next()){
                Todo todo = new Todo(
                  rs.getInt("id"),
                  rs.getString("content"),
                  rs.getBoolean("status")
                );
                list.add(todo);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            // dóng kết nối
            ConnectionDB.closeConnection(conn);
        }
    }
    // hàm xóa
    private void delete(int id){
        //b1 mở kết nối
        Connection conn = ConnectionDB.getConnection();
        // b2 tạo truy vấn
        try {
            CallableStatement call = conn.prepareCall("delete from todo where  id = ?");
            call.setInt(1,id);
            // thực thi câu lệnh
            int count = call.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            // dóng kết nối
            ConnectionDB.closeConnection(conn);
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
                    response.sendRedirect("/todo?action=GETALL");
                    break;

                case "UPDATE":
                    int idEdit = Integer.parseInt(request.getParameter("id"));
                    String contentUpdate = request.getParameter("content");
                    boolean statusUpdate = Boolean.parseBoolean(request.getParameter("status"));
                    response.sendRedirect("/todo?action=GETALL");
                    break;
            }
        }
    }
}