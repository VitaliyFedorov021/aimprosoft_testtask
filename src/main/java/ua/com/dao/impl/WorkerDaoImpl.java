package ua.com.dao.impl;

import ua.com.DataSource;
import ua.com.dao.WorkerDao;
import ua.com.model.Worker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkerDaoImpl implements WorkerDao {
    private static WorkerDaoImpl instance;
    private final DataSource dataSource = new DataSource();
    private static final String ADD_WORKER = "INSERT INTO workers (login, departmentId, firstName, lastName, birthdayDate, email) values (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_WORKERS = "SELECT * FROM workers";
    private static final String SELECT_WORKERS_ID = "SELECT * FROM workers WHERE id = ?";
    private static final String SELECT_WORKERS_LOGIN = "SELECT * FROM workers WHERE login = ?";
    private static final String DELETE_WORKER_ID = "DELETE FROM workers WHERE id = ?";
    private static final String DELETE_WORKER_LOGIN = "DELETE FROM workers WHERE login = ?";
    private static final String UPDATE_WORKER = "UPDATE workers SET login = ?, departmentId = ?, firstName = ?, lastName = ?, birthdayDate = ?, email = ? WHERE id = ?";

    private WorkerDaoImpl() {
    }

    public static synchronized WorkerDaoImpl getInstance() {
        if (instance == null) {
            instance = new WorkerDaoImpl();
        }
        return instance;
    }

    @Override
    public void addWorker(Worker worker) throws SQLIntegrityConstraintViolationException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pStatement = connection.prepareStatement(ADD_WORKER)) {
            connection.setAutoCommit(false);
            pStatement.setString(1, worker.getLogin());
            pStatement.setInt(2, worker.getDepartmentId());
            pStatement.setString(3, worker.getFirstName());
            pStatement.setString(4, worker.getLastName());
            pStatement.setDate(5, new java.sql.Date(worker.getBirthdayDate().getTime()));
            pStatement.setString(6, worker.getEmail());
            pStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Worker> selectAllWorkers() {
        List<Worker> workers = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pStatement = connection.prepareStatement(SELECT_WORKERS)) {
            ResultSet rSet = pStatement.executeQuery();
            while (rSet.next()) {
                workers.add(parseWorker(rSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return workers;
    }

    @Override
    public Worker selectWorkerById(int id) {
        Worker worker = new Worker();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pStatement = connection.prepareStatement(SELECT_WORKERS_ID)) {
            pStatement.setInt(1, id);
            ResultSet rSet = pStatement.executeQuery();
            while (rSet.next()) {
                worker = parseWorker(rSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return worker;
    }

    @Override
    public Worker selectWorkerByLogin(String login) {
        Worker worker = new Worker();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pStatement = connection.prepareStatement(SELECT_WORKERS_LOGIN)) {
            pStatement.setString(1, login);
            ResultSet rSet = pStatement.executeQuery();
            while (rSet.next()) {
                worker = parseWorker(rSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return worker;
    }

    @Override
    public void deleteWorker(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pStatement = connection.prepareStatement(DELETE_WORKER_ID)) {
            connection.setAutoCommit(false);
            pStatement.setInt(1, id);
            pStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteWorker(String login) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pStatement = connection.prepareStatement(DELETE_WORKER_LOGIN)) {
            connection.setAutoCommit(false);
            pStatement.setString(1, login);
            pStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateWorker(int id, Worker worker) throws SQLIntegrityConstraintViolationException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement pStatement = connection.prepareStatement(UPDATE_WORKER)) {
            connection.setAutoCommit(false);
            pStatement.setString(1, worker.getLogin());
            pStatement.setInt(2, worker.getDepartmentId());
            pStatement.setString(3, worker.getFirstName());
            pStatement.setString(4, worker.getLastName());
            pStatement.setDate(5, new java.sql.Date(worker.getBirthdayDate().getTime()));
            pStatement.setString(6, worker.getEmail());
            pStatement.setInt(7, id);
            pStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Worker parseWorker(ResultSet rSet) throws SQLException {
        Worker worker = new Worker();
        worker.setId(rSet.getInt("id"));
        worker.setLogin(rSet.getString("login"));
        worker.setDepartmentId(rSet.getInt("departmentId"));
        worker.setFirstName(rSet.getString("firstName"));
        worker.setLastName(rSet.getString("lastName"));
        worker.setEmail(rSet.getString("email"));
        worker.setBirthdayDate(rSet.getDate("birthdayDate"));
        return worker;
    }
}
