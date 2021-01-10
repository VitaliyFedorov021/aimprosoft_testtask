package ua.com.services;

import ua.com.exceptions.IncorrectInputException;
import ua.com.model.Department;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface DepartmentService {

    void addDepartment(Department department) throws IncorrectInputException, SQLIntegrityConstraintViolationException;

    List<Department> selectAllDepartments();

    Department selectById(int dId);

    Department selectByUserId(int uId);

    void deleteDepartmentById(int id);

    void updateDepartment(int id, Department department) throws IncorrectInputException, SQLIntegrityConstraintViolationException;
}
