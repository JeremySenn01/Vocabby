package ch.bbw.senn.Vocabby.dao;

import java.util.List;
import java.util.Optional;

public interface IDao<T> {
     
    Optional<T> getById(String username);
     
    List<T> getAll();
     
    void save(T t);
     
    void update(T t, String[] params);
     
    void delete(T t);
}