package ra.ontap.dao.todo;

import ra.ontap.model.Todo;
import ra.ontap.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TodoDaoImpl implements ITodoDao{
    @Override
    public List<Todo> findAll() {
        List<Todo> list = new ArrayList<>();
        // B1 mở kết nôi
        Connection conn = ConnectionDB.getConnection();
        try {
            // B2 : Tạo cau truy van thong qua CallableStatement
            CallableStatement call = conn.prepareCall("select * from todo");
            // B3 : Truyền đối số nếu có
            // b4 : Thực thi truy vấn
            ResultSet rs = call.executeQuery();
            // b5 : Xử lí ke quả trả ve nếu có
            while (rs.next()){
                Todo todo = new Todo(
                        rs.getInt("id"),
                        rs.getString("content"),
                        rs.getBoolean("status")
                );
                list.add(todo);
            }
            return list;
            // b6 : Đóng ket nối
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(conn);
        }
    }

    @Override
    public void create(Todo todo) {
        // B1 mở kết nôi
        Connection conn = ConnectionDB.getConnection();
        try {
            // B2 : Tạo cau truy van thong qua CallableStatement
            CallableStatement call = conn.prepareCall("insert into todo(content, status) values (?,false)");
            // B3 : Truyền đối số nếu có
            call.setString(1,todo.getContent());
            // b4 : Thực thi truy vấn
            int count  = call.executeUpdate();
            // b5 : Xử lí ke quả trả ve nếu có
            // b6 : Đóng ket nối
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(conn);
        }
    }

    @Override
    public Todo findById(Integer id) {
        Todo todo = null;
        // B1 mở kết nôi
        Connection conn = ConnectionDB.getConnection();
        try {
            // B2 : Tạo cau truy van thong qua CallableStatement
            CallableStatement call = conn.prepareCall("select * from todo where  id =?");
            // B3 : Truyền đối số nếu có
            call.setInt(1,id);
            // b4 : Thực thi truy vấn
            ResultSet rs = call.executeQuery();
            // b5 : Xử lí ke quả trả ve nếu có
            if (rs.next()){
                todo = new Todo(
                        rs.getInt("id"),
                        rs.getString("content"),
                        rs.getBoolean("status")
                );
            }
            return todo;
            // b6 : Đóng ket nối
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(conn);
        }
    }

    @Override
    public void update(Todo todo) {
        // B1 mở kết nôi
        Connection conn = ConnectionDB.getConnection();
        try {
            // B2 : Tạo cau truy van thong qua CallableStatement
            CallableStatement call = conn.prepareCall("update todo set content=?, status=? where id = ?");
            // B3 : Truyền đối số nếu có
            call.setString(1,todo.getContent());
            call.setBoolean(2,todo.getStatus());
            call.setInt(3,todo.getId());
            // b4 : Thực thi truy vấn
            int count  = call.executeUpdate();
            // b5 : Xử lí ke quả trả ve nếu có
            // b6 : Đóng ket nối
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(conn);
        }
    }

    @Override
    public void deleteById(Integer id) {
        // B1 mở kết nôi
        Connection conn = ConnectionDB.getConnection();
        try {
            // B2 : Tạo cau truy van thong qua CallableStatement
            CallableStatement call = conn.prepareCall("delete from todo where  id = ?");
            // B3 : Truyền đối số nếu có
            call.setInt(1,id);
            // b4 : Thực thi truy vấn
            int count  = call.executeUpdate();
            // b5 : Xử lí ke quả trả ve nếu có
            // b6 : Đóng ket nối
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(conn);
        }
    }
}
