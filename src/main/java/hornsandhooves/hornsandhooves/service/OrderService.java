package hornsandhooves.hornsandhooves.service;

import hornsandhooves.hornsandhooves.dao.DepartmentDao;
import hornsandhooves.hornsandhooves.dao.ExecutorDao;
import hornsandhooves.hornsandhooves.dao.OrderDao;
import hornsandhooves.hornsandhooves.dto.DaysAndHoursDto;
import hornsandhooves.hornsandhooves.model.Department;
import hornsandhooves.hornsandhooves.model.Executor;
import hornsandhooves.hornsandhooves.model.Order;
import org.assertj.core.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderDao orderDao;
    private final ExecutorDao executorDao;
    private final DepartmentDao departmentDao;

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

    @Autowired
    public OrderService(OrderDao orderDao, ExecutorDao executorDao, DepartmentDao departmentDao) {
        this.orderDao = orderDao;
        this.executorDao = executorDao;
        this.departmentDao = departmentDao;
    }

    @Transactional
    public Order createOrderAndSetExecutor(Order order) {
        final List<Executor> executors = executorDao.findExecutors(order.getProductionType());
        order.setExecutors(executors);
        orderDao.save(order);
        return order;
    }

    @Transactional
    public List<Order> getByDepartment(Department department) {
        department = departmentDao.get(department.getId());
        return department.getExecutors().stream()
                .flatMap(executor -> executor.getOrders().stream())
                .collect(Collectors.toList());
    }

    @Transactional
    public List<Order> getByExecutors(List<Executor> executors){
        return executors.stream().map(executor -> executorDao.get(executor.getId()))
                .flatMap(executor -> executor.getOrders().stream())
                .collect(Collectors.toList());
    }

    @Transactional
    public DaysAndHoursDto evaluateTimeToComplete(Order order){
        order = orderDao.get(order.getId());
        int hours = (int) (TimeUnit.MILLISECONDS.toHours(DateUtil.now().getTime())
                        -TimeUnit.MILLISECONDS.toHours(order.getDateEnd().getTime()));
        int days = hours / 24;
        return new DaysAndHoursDto(days, hours - days*24);
    }
}
