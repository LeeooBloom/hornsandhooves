package hornsandhooves.hornsandhooves;

import hornsandhooves.hornsandhooves.dao.DepartmentDao;
import hornsandhooves.hornsandhooves.dao.ExecutorDao;
import hornsandhooves.hornsandhooves.dao.OrderDao;
import hornsandhooves.hornsandhooves.model.Department;
import hornsandhooves.hornsandhooves.model.Executor;
import hornsandhooves.hornsandhooves.model.Order;
import hornsandhooves.hornsandhooves.model.ProductionType;
import org.assertj.core.util.DateUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.Assert.*;

@SpringBootTest
public class OrderDaoTest extends HornsandhoovesApplicationTests {

    @Autowired
    OrderDao orderDao;

    @Autowired
    ExecutorDao executorDao;

    @Autowired
    DepartmentDao departmentDao;

    @Test
    public void insertTest() throws Exception{

        final Department department = new Department();
        department.setName("department");
        department.setProductionType(ProductionType.OFFICE_FURNITURE);
        departmentDao.save(department);

        final Executor executor1 = new Executor();
        executor1.setName("executor1");
        executor1.setDepartment(department);
        executorDao.save(executor1);

        final Executor executor2 = new Executor();
        executor2.setName("executor2");
        executor2.setDepartment(department);
        executorDao.save(executor2);

        final Order order = new Order();
        order.setProductionType(ProductionType.OFFICE_FURNITURE);
        order.setDateEnd(DateUtil.now());
        order.setName("order");
        order.getExecutors().add(executor1);
        order.getExecutors().add(executor2);

        orderDao.save(order);

        final Order savedOrder = orderDao.get(order.getId());
        assertNotNull(savedOrder);
        assertEquals(savedOrder.getExecutors().size(), 2);
    }

}
