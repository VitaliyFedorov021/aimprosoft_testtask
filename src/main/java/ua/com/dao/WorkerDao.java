package ua.com.dao;

import ua.com.model.Worker;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface WorkerDao {
    void addWorker(Worker worker) throws SQLIntegrityConstraintViolationException;

    List<Worker> selectAllWorkers();

    Worker selectWorkerById(int id);

    Worker selectWorkerByLogin(String login);

    void deleteWorker(int id);

    void deleteWorker(String login);

    void updateWorker(int id, Worker worker) throws SQLIntegrityConstraintViolationException;
}
