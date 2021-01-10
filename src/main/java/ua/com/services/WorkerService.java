package ua.com.services;

import ua.com.exceptions.IncorrectInputException;
import ua.com.model.Worker;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;
import java.util.List;

public interface WorkerService {

    void addWorker(Worker worker) throws IncorrectInputException, SQLIntegrityConstraintViolationException, ParseException;

    List<Worker> selectAllWorkers();

    Worker selectWorkerById(int id);

    Worker selectWorkerByLogin(String login);

    void deleteWorker(int id);

    void deleteWorker(String login);

    void updateWorker(int id, Worker worker) throws IncorrectInputException, SQLIntegrityConstraintViolationException, ParseException;
}
