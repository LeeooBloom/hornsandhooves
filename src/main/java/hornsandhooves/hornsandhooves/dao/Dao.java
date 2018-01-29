package hornsandhooves.hornsandhooves.dao;

import hornsandhooves.hornsandhooves.model.PersistentModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

@Repository
public abstract class Dao<T extends PersistentModel> implements IDao<T> {

    @Autowired
    protected  SessionFactory sessionFactory;

    @Override
    public void save(T model) {
        final Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(model);
        currentSession.flush();
    }

    @Override
    public void update(T model) {
        final Session currentSession = sessionFactory.getCurrentSession();
        currentSession.update(model);
        currentSession.flush();
    }

    @Override
    public void delete(T model) {
        final Session currentSession = sessionFactory.getCurrentSession();
        currentSession.delete(model);
        currentSession.flush();
    }

    @Override
    public T get(long id) {
        final Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(getEntityClass(), id);
    }

    public abstract Class<T> getEntityClass();
}
