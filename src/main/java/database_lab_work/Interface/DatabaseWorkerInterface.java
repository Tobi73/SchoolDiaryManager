package database_lab_work.Interface;

import java.util.List;

/**
 * Created by gman0_000 on 26.04.2017.
 */
public interface DatabaseWorkerInterface{

    <T> T find(Class<T> tClass, long id);

    <T> void update(Class<T> tClass, long id, T updatedEntity);

    <T> void insert(Class<T> tClass, Object newEntity);

    <T> void remove(Class<T> tClass, long id);

    <T> List<T> getAllData(String entityName);



}
