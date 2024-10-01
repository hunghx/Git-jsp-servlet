package ra.ontap;


import java.sql.*;
import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        // B1 khai báo Driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
//            B2 mở kết nối
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc"
                    ,"root"
                    ,"hung18061999");
            System.out.println("Ket noi thanh cong");
            // thuc hien truy vaans du lieu
            // CRUD bang Customer
            // 3 interface dung de taoj SQL
            // Statement : its tinh nang, chi thuc thi các câu lệnh SQL tĩnh
//            Select * from cutomer whrere id = 1
//            Statement st = conn.createStatement(); // tạo cau leenhj sql
            // thưc thi câu lệnh : execute() => boolean, executeQuery() => ResultSet, executeUpdate() => int
            // lấy về dữ liệu danh sách khách hàng
//            ResultSet rs = st.executeQuery("Select * from customer");

//            while (rs.next()){
//                // đổ ra thông tin của bản ghi đang đc duyet
//                System.out.printf("Khách hàng : ID : %s | Name : %s | Email : %s | Address : %s \n",
//                       rs.getInt("id")
//                        ,rs.getString("name")
//                        ,rs.getString("email")
//                        ,rs.getString("address"));
//            }

            // PrepareStatement : cho phép truyển tham số
//            PreparedStatement pre = conn.prepareStatement("insert into customer(name, address, email) values (?,?,?)"); // chuẩn bị câu lệnh
//            // truyển đối số cho tham số
//            pre.setString(1,"Nguyen Thi Bình");
//            pre.setString(2,"HCM");
//            pre.setString(3,"binh@gmail.com");
//            // thực thi
//            int count = pre.executeUpdate();
//            System.out.println("count : "+count);

            // CallableStatement : tối ưu nhất , cho phép gọi thủ tục
            CallableStatement call = conn.prepareCall("{call insert_customer(?,?,?)}");
            call.setString(1,"Nguyen Thi Sơn");
            call.setString(2,"Hà Nội");
            call.setString(3,"son@gmail.com");
            int count =   call.executeUpdate();
            System.out.println("count : "+count);
            // dong ket noi
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
