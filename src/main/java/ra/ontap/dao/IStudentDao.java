package ra.ontap.dao;

import ra.ontap.model.Student;

import java.util.List;

public interface IStudentDao {
    List<Student> findAllStudents();
    Student findStudentById(int id);
    void save(Student student);
    void delete(int id);
}
