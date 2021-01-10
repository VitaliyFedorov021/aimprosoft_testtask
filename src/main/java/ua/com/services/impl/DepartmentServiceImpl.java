package ua.com.services.impl;

import ua.com.dao.DepartmentDao;
import ua.com.dao.impl.DepartmentDaoImpl;
import ua.com.exceptions.IncorrectInputException;
import ua.com.model.Department;
import ua.com.services.DepartmentService;
import ua.com.util.Checker;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentDao departmentDao = DepartmentDaoImpl.getInstance();

    @Override
    public void addDepartment(Department department) throws IncorrectInputException, SQLIntegrityConstraintViolationException{
        if (Checker.checkDepName(department.getName())) {
            departmentDao.addDepartment(department);
            return;
        }
        throw new IncorrectInputException("Incorrect name");
    }

    @Override
    public List<Department> selectAllDepartments() {
        return departmentDao.selectAllDepartments();
    }

    @Override
    public Department selectById(int dId) {
        return departmentDao.selectById(dId);
    }

    @Override
    public Department selectByUserId(int uId) {
        return departmentDao.selectByUserId(uId);
    }

    @Override
    public void deleteDepartmentById(int id) {
        departmentDao.deleteDepartmentById(id);
    }

    @Override
    public void updateDepartment(int id, Department department) throws IncorrectInputException, SQLIntegrityConstraintViolationException {
        if (Checker.checkDepName(department.getName())) {
            departmentDao.updateDepartment(id, department);
            return;
        }
        throw new IncorrectInputException("Incorrect name");
    }
}
