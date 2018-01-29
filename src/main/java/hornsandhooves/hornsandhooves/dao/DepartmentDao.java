package hornsandhooves.hornsandhooves.dao;

import hornsandhooves.hornsandhooves.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;

@Repository
public class DepartmentDao extends Dao<Department> {

    @Override
    public Class<Department> getEntityClass() {
        return Department.class;
    }
}
