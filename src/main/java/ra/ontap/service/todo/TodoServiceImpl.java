package ra.ontap.service.todo;

import org.springframework.stereotype.Component;
import ra.ontap.dao.todo.ITodoDao;
import ra.ontap.dao.todo.TodoDaoImpl;
import ra.ontap.model.Todo;

import java.util.List;

public class TodoServiceImpl implements ITodoService{
    private ITodoDao todoDao ;
    @Override
    public List<Todo> findAll() {
        return todoDao.findAll();
    }

    @Override
    public Todo findById(Integer id) {
        return todoDao.findById(id);
    }

    @Override
    public void save(Todo todo) {
        if (todo.getId() == null){
            // chuc nang them moi
            todoDao.create(todo);
        }else {
            // cap nhap thong tin
            todoDao.update(todo);
        }
    }

    @Override
    public void deleteById(Integer id) {
        todoDao.deleteById(id);
    }
}
