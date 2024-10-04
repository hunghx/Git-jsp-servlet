package ra.ontap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ra.ontap.dao.IStudentDao;
import ra.ontap.model.Student;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    IStudentDao studentDao;
    @GetMapping
    public String test(){
//        List<Student> students = studentDao.findAllStudents();

        Student s =  new Student(3,"sontx",22);
        studentDao.save(s);
        return "home";
    }
}
