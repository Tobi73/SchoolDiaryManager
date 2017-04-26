package database_lab_work.Repository;

import database_lab_work.Interface.DatabaseWorkerInterface;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by gman0_000 on 26.04.2017.
 */
public class DatabaseController implements DatabaseWorkerInterface{

    private static DatabaseController instance;


    private static final String PERSISTENCE_UNIT_NAME = "school_diary";
    private static EntityManagerFactory factory;
    private EntityManager manager;

    private DatabaseController(){
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        manager = factory.createEntityManager();
    }

    public static DatabaseController getInstance(){
        if(instance == null){
            instance = new DatabaseController();
        }
        return instance;
    }

    @Override
    public <T> T find(Class<T> tClass, long id) {
        return manager.find(tClass, id);
    }

    @Override
    public <T> void update(Class<T> entityClass, long id, T updatedEntity) {
        try{
            manager.getTransaction().begin();
            manager.merge(updatedEntity);
            manager.flush();
            manager.getTransaction().commit();
        } catch (Exception e){
            manager.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public <T> void insert(Class<T> tClass, Object newEntity) {
        try{
            manager.getTransaction().begin();
            manager.persist(newEntity);
            manager.flush();
            manager.getTransaction().commit();
        } catch (Exception e){
            manager.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public <T> void remove(Class<T> entityClass, long id) {
        try{
            manager.getTransaction().begin();
            T entity = manager.find(entityClass, id);
            manager.remove(entity);
            manager.flush();
            manager.getTransaction().commit();
        } catch (Exception e){
            manager.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public <T> List<T> getAllData(String entityName) {
        try{
            manager.getTransaction().begin();
            Query q = manager.createQuery(String.format("select p from %s p", entityName));
            List<T> entities = q.getResultList();
            manager.getTransaction().commit();
            return entities;
        } catch (Exception e){
            manager.getTransaction().rollback();
            throw e;
        }
    }
}
