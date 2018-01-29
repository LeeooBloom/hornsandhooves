package hornsandhooves.hornsandhooves.dao;

import hornsandhooves.hornsandhooves.model.Executor;
import hornsandhooves.hornsandhooves.model.ProductionType;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
public class ExecutorDao extends Dao<Executor> {

    @SuppressWarnings("unchecked")
    public List<Executor> findExecutors(ProductionType productionType) {
        final Session session = sessionFactory.getCurrentSession();
       return session.createCriteria(Executor.class)
                .createAlias("department","department")
                .add(Restrictions.eq("department.productionType", productionType))
                .list();
    }

    @Override
    public Class<Executor> getEntityClass() {
        return Executor.class;
    }
}
