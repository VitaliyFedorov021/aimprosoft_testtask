package ua.com.dao.impl;

import ua.com.DataSource;
import ua.com.dao.DepartmentDao;
import ua.com.model.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoImpl implements DepartmentDao {
    private static DepartmentDaoImpl instance;
    private static final String ADD_DEP = "INSERT INTO departments (address, name) values (?, ?)";
    private static final String SELECT_ALL_DEPS = "SELECT * FROM departments";
    private static final String SELECT_DEP_ID = "SELECT * FROM departments WHERE id = ?";
    private static final String SELECT_U_ID = "SELECT * FROM departments d JOIN workers w ON w.departmentId = d.id WHERE w.id = ?";
    private static final String DELETE_DEP = "DELETE FROM departments WHERE id = ?";
    private static final String UPDATE_DEP = "UPDATE departments SET address = ?, name = ? WHERE id = ?";
    private final DataSource dataSource = new DataSource();

    private DepartmentDaoImpl() {

    }

    public static synchronized DepartmentDaoImpl getInstance() {
        if (instance == null) {
            instance = new DepartmentDaoImpl();
        }
        return instance;
    }

    @Override
    public void addDepartment(Department department) throws SQLIntegrityConstraintViolationException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pStatement = connection.prepareStatement(ADD_DEP)) {
            connection.setAutoCommit(false);
            pStatement.setString(1, department.getAddress());
            pStatement.setString(2, department.getName());
            pStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Department> selectAllDepartments() {
        List<Department> departments = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pStatement = connection.prepareStatement(SELECT_ALL_DEPS)) {
            ResultSet rSet = pStatement.executeQuery();
            while (rSet.next()) {
                departments.add(parseResultSet(rSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }

    @Override
    public Department selectById(int dId) {
        Department department = new Department();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pStatement = connection.prepareStatement(SELECT_DEP_ID)) {
            pStatement.setInt(1, dId);
            ResultSet rSet = pStatement.executeQuery();
            while (rSet.next()) {
                department = parseResultSet(rSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return department;
    }

    @Override
    public Department selectByUserId(int uId) {
        Department department = new Department();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pStatement = connection.prepareStatement(SELECT_U_ID)) {
            pStatement.setInt(1, uId);
            ResultSet rSet = pStatement.executeQuery();
            department = parseResultSet(rSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return department;
    }

    @Override
    public void updateDepartment(int id, Department department) throws SQLIntegrityConstraintViolationException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pStatement = connection.prepareStatement(UPDATE_DEP)) {
            connection.setAutoCommit(false);
            pStatement.setString(1, department.getAddress());
            pStatement.setString(2, department.getName());
            pStatement.setInt(3, id);
            pStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDepartmentById(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pStatement = connection.prepareStatement(DELETE_DEP)) {
            connection.setAutoCommit(false);
            pStatement.setInt(1, id);
            pStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Department parseResultSet(ResultSet resultSet) throws SQLException {
        Department department = new Department();
        department.setAddress(resultSet.getString("address"));
        department.setName(resultSet.getString("name"));
        department.setId(resultSet.getInt("id"));
        return department;
    }
}
