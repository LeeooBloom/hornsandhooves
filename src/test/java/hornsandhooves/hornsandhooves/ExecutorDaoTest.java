package hornsandhooves.hornsandhooves;

import hornsandhooves.hornsandhooves.dao.DepartmentDao;
import hornsandhooves.hornsandhooves.dao.ExecutorDao;
import hornsandhooves.hornsandhooves.model.Department;
import hornsandhooves.hornsandhooves.model.Executor;
import hornsandhooves.hornsandhooves.model.ProductionType;
import org.hibernate.Session;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;

@SpringBootTest
public class ExecutorDaoTest extends HornsandhoovesApplicationTests {

    @Autowired
    DepartmentDao departmentDao;
    @Autowired
    ExecutorDao executorDao;

    @Test
    public void insertTest() throws Exception{
        final Department department = new Department();
        department.setName("department");
        department.setProductionType(ProductionType.OFFICE_FURNITURE);
        departmentDao.save(department);

        final Executor executor = new Executor();
        executor.setName("executor");
        executor.setDepartment(department);
        executorDao.save(executor);

        final Executor savedExecutor = executorDao.get(executor.getId());
        assertNotNull(savedExecutor);
        assertEquals(savedExecutor.getDepartment(), department);
    }

    @Test
    public void updateTest() throws Exception{
        final Department department = new Department();
        department.setName("department");
        department.setProductionType(ProductionType.OFFICE_FURNITURE);
        departmentDao.save(department);

        final Department newDepartment = new Department();
        newDepartment.setName("newDepartment");
        newDepartment.setProductionType(ProductionType.OFFICE_FURNITURE);
        departmentDao.save(newDepartment);

        final Executor executor = new Executor();
        executor.setName("executor");
        executor.setDepartment(department);
        executorDao.save(executor);

        executor.setName("updatedExecutor");
        executor.setDepartment(newDepartment);
        executorDao.update(executor);

        final Executor updatedExecutor = executorDao.get(executor.getId());
        assertEquals("updatedExecutor",updatedExecutor.getName());
        assertEquals(newDepartment,updatedExecutor.getDepartment());
    }

    @Test
    public void removeTest() throws Exception{
        final Department department = new Department();
        department.setName("department");
        department.setProductionType(ProductionType.OFFICE_FURNITURE);
        departmentDao.save(department);

        final Executor executor = new Executor();
        executor.setName("executor");
        executor.setDepartment(department);
        executorDao.save(executor);

        executorDao.delete(executor);

        final Executor deleted = executorDao.get(executor.getId());
        assertNull(deleted);
    }
}
