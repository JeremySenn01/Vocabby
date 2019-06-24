package ch.bbw.senn.Vocabby.dao;

import java.util.List;
import java.util.Optional;

public interface IDao<T> {
     
    Optional<T> getById(String id);
     
    List<T> getAll(String id_fk);
     
    boolean save(T t);
     
    void update(T t, String[] params);
     
    boolean delete(T t);
    
    void deleteAll(List<T> t);
}