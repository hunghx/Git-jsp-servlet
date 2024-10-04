package ra.ontap.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ra.ontap.model.Student;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Repository
public class StudentDaoImp implements IStudentDao{
    @Autowired
    SessionFactory sessionFactory;
    @Override
    public List<Student> findAllStudents() {
        Session session = sessionFactory.openSession();
        return session.createQuery("select S from Student S", Student.class).list();// HQL
    }

    @Override
    public Student findStudentById(int id) {
        Session session = sessionFactory.openSession();
        return session.find(Student.class, id);
    }

    @Override
    public void save(Student student) {

        Session session = sessionFactory.openSession();
        Transaction tran = session.beginTransaction();
        if(student.getId()==0){
            session.saveOrUpdate(student);
//            session.save(student)
        }else {
            // lấy ra thông tin cũ
            Student old = findStudentById(student.getId());
            old.setAge(student.getAge());
            old.setName(student.getName());
//            session.saveOrUpdate(old);
            session.update(student);
        }
        tran.commit();
        session.close();

    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.openSession();
        Transaction tran = session.beginTransaction();
        session.delete(findStudentById(id));
        tran.commit();
        session.close();
    }
}
