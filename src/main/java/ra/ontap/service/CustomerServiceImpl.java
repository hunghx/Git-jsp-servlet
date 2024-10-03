package ra.ontap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.ontap.dao.ICustomerDao;
import ra.ontap.model.Customer;

import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService{
    @Autowired
    private ICustomerDao customerDao;
    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    @Override
    public Customer findById(int id) {
        return customerDao.findById(id);
    }

    @Override
    public void save(Customer customer) {
        customerDao.save(customer);
    }

    @Override
    public void delete(int id) {
        customerDao.delete(id);
    }
}
