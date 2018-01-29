package hornsandhooves.hornsandhooves.controller;

import hornsandhooves.hornsandhooves.dao.DepartmentDao;
import hornsandhooves.hornsandhooves.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/department")
public class DepartmentController implements ICrudController<Department,DepartmentDao>{

    private final DepartmentDao departmentDao;

    @Autowired
    public DepartmentController(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public DepartmentDao getDao() {
        return departmentDao;
    }
}
