package ua.com.dao;

import ua.com.exceptions.NoSuchDepartmentException;
import ua.com.model.Department;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface DepartmentDao {
    void addDepartment(Department department) throws SQLIntegrityConstraintViolationException;

    List<Department> selectAllDepartments();

    Department selectById(int dId);

    Department selectByUserId(int uId);

    void deleteDepartmentById(int id);

    void updateDepartment(int id, Department department) throws SQLIntegrityConstraintViolationException;

}
