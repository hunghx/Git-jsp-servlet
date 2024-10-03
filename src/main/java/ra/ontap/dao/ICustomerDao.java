package ra.ontap.dao;

import ra.ontap.model.Customer;

import java.util.List;

public interface ICustomerDao {
    List<Customer> findAll();
    Customer findById(int id);
    void save(Customer customer); // vừa add vừa update
    void delete(int id);
}
