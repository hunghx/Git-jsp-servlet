package ra.ontap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ra.ontap.model.Customer;
import ra.ontap.service.ICustomerService;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @GetMapping
    public String showListCustomers(Model model){
        List<Customer> list = customerService.findAll();
        model.addAttribute("customers",list);
        // trả về ten view
        return "customers";
    }
    // xử lí xóa
    @GetMapping("/customer/delete")
    public String deleteCustomer(@RequestParam int id){
        customerService.delete(id);
        return "redirect:/";
    }
    // hiê thị form
    @GetMapping("/customer/add")
    public String showFormAdd(){
        return "add-customer";
    }

    // xử lí thêm mới
    @PostMapping("/customer/add")
    public String addCustomer(@RequestParam String name,@RequestParam String email,@RequestParam String address){
        // chuyen đổi thành customer
        Customer newCustomer = new Customer(0,name,address,email);
        customerService.save(newCustomer);
        return "redirect:/";
    }

    // đổ ra du liệu cũ
    @GetMapping("/customer/edit")
    public String editCustomer(@RequestParam int id, Model model){
        // lây customer
        Customer cus = customerService.findById(id);
        model.addAttribute("customer",cus);
        return "edit-customer";

    }
    // xử li cap nhat
    @PostMapping("/customer/update")
    public String updateCustomer(@ModelAttribute Customer customer){
        customerService.save(customer);
        return "redirect:/";
    }
}
