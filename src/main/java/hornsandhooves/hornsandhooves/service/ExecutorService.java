package hornsandhooves.hornsandhooves.service;

import hornsandhooves.hornsandhooves.dao.ExecutorDao;
import hornsandhooves.hornsandhooves.dao.OrderDao;
import hornsandhooves.hornsandhooves.model.Executor;
import hornsandhooves.hornsandhooves.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ExecutorService {

    private final ExecutorDao executorDao;
    private final OrderDao orderDao;

    @Autowired
    public ExecutorService(ExecutorDao executorDao, OrderDao orderDao) {
        this.executorDao = executorDao;
        this.orderDao = orderDao;
    }


    @Transactional
    public Executor deleteExecutorAndResetOrders(Executor deletedExecutor) {
        final List<Order> orders = deletedExecutor.getOrders();
        executorDao.delete(deletedExecutor);
        if (!orders.isEmpty()) {
            orders.forEach(order -> {
                final List<Executor> executors = executorDao.findExecutors(order.getProductionType());
                order.setExecutors(executors);
                orderDao.update(order);
            });
        }
        return deletedExecutor;
    }

}
