package ra.ontap.dao;

import java.util.List;

public interface IGenericDao <T, E> {
    List<T> findAll();
    void create(T t);
    T findById(E id);
    void update(T t);
    void deleteById(E id);
}
