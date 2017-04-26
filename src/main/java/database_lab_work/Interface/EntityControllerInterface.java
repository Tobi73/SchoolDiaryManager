package database_lab_work.Interface;

import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Created by gman0_000 on 26.04.2017.
 */
public interface EntityControllerInterface<T> {

    ResponseEntity<List<T>> getAllData();

    ResponseEntity<T> getById(Long id);

    ResponseEntity<?> createNewInstance(T entity);

    ResponseEntity<?> updateInstance(Long id, T updatedEntity);

    ResponseEntity<?> removeInstance(Long id);


}
