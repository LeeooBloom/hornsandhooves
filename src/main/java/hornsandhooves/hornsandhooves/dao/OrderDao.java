package hornsandhooves.hornsandhooves.dao;

import hornsandhooves.hornsandhooves.model.Department;
import hornsandhooves.hornsandhooves.model.Order;
import org.assertj.core.util.DateUtil;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
public class OrderDao extends Dao<Order> {

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Order> getAll(){
        final Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.createCriteria(Order.class)
                .list();
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Order> getAllUncompleted(){
        final Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.createCriteria(Order.class)
                .add(Restrictions.le("dateEnd", DateUtil.now()))
                .list();
    }

    @Override
    public Class<Order> getEntityClass() {
        return Order.class;
    }
}
