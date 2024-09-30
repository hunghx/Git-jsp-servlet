package ra.ontap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        System.out.println("Vào pt");
        // lấy tham số gửi theo request
        String id = req.getParameter("id");
        String name = req.getParameter("name");
//        int age = Integer.parseInt(req.getParameter("age"));
        System.out.println("Xu li request");
        // điều hướng request tới home.jsp
        req.setAttribute("id",id);
        req.setAttribute("name",name);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/home.jsp");
        // chuyển hướng request và reponse
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Vào get");
        // lấy tham số gửi theo request
        String id = req.getParameter("id");
        String name = req.getParameter("name");
//        int age = Integer.parseInt(req.getParameter("age"));
        System.out.println("Xu li request");
    }
    // lớp xử lí request gửi lên từ người dùng
}
