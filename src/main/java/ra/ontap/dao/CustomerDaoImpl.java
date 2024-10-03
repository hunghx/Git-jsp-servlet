package ra.ontap.dao;

import org.springframework.stereotype.Component;
import ra.ontap.model.Customer;
import ra.ontap.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component // @Repository
public class CustomerDaoImpl implements ICustomerDao{
    @Override
    public List<Customer> findAll() {
        List<Customer> list = new ArrayList<>();
        Connection conn = ConnectionDB.getConnection();
        try{
            CallableStatement call = conn.prepareCall("select  * from customer");
            ResultSet rs = call.executeQuery();
            while (rs.next()){
                Customer customer = new Customer(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("email")
                );
                list.add(customer);
            }
            return list;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(conn);
        }
    }

    @Override
    public Customer findById(int id) {
        Connection conn = ConnectionDB.getConnection();
        try{
            CallableStatement call = conn.prepareCall("select  * from customer where id = ?");
            call.setInt(1,id);
            ResultSet rs = call.executeQuery();
            if (rs.next()){
                return new Customer(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("email")
                );
            }
            return null;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(conn);
        }
    }

    @Override
    public void save(Customer customer) {
        String insert = "insert into customer(name,email,address) values(?,?,?)";
        String update = "update customer set name= ?,email=?,address=? where id = ?";
        Connection conn = ConnectionDB.getConnection();
        CallableStatement call = null;
        try{
             if (customer.getId()==0){
                 call = conn.prepareCall(insert);
             }else {
                 call = conn.prepareCall(update);
             }
            // truyền đô số
            call.setString(1, customer.getName());
            call.setString(2, customer.getEmail());
            call.setString(3, customer.getAddress());
            // kiểm tra
            if (customer.getId() != 0){
                call.setInt(4, customer.getId());
            }
            call.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(conn);
        }
    }

    @Override
    public void delete(int id) {
        Connection conn = ConnectionDB.getConnection();
        try{
            CallableStatement call = conn.prepareCall("delete from customer where id = ?");
            call.setInt(1,id);
            call.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(conn);
        }
    }
}
