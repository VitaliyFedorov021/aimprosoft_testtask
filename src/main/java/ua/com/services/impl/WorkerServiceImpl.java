package ua.com.services.impl;

import ua.com.dao.WorkerDao;
import ua.com.dao.impl.WorkerDaoImpl;
import ua.com.exceptions.IncorrectInputException;
import ua.com.model.Worker;
import ua.com.services.WorkerService;
import ua.com.util.Checker;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;
import java.util.List;

public class WorkerServiceImpl implements WorkerService {
    private final WorkerDao workerDao = WorkerDaoImpl.getInstance();

    @Override
    public void addWorker(Worker worker) throws IncorrectInputException, SQLIntegrityConstraintViolationException, ParseException {
        if (!(Checker.checkLogin(worker.getLogin()) || Checker.checkEmail(worker.getEmail()) ||
                Checker.checkName(worker.getFirstName()) || Checker.checkName(worker.getLastName()) ||
                Checker.checkDate(worker.getBirthdayDate()))) {
            throw new IncorrectInputException("Incorrect login");
        }
        workerDao.addWorker(worker);
        return;
    }

    @Override
    public List<Worker> selectAllWorkers() {
        return workerDao.selectAllWorkers();
    }

    @Override
    public Worker selectWorkerById(int id) {
        return workerDao.selectWorkerById(id);
    }

    @Override
    public Worker selectWorkerByLogin(String login) {
        return workerDao.selectWorkerByLogin(login);
    }

    @Override
    public void deleteWorker(int id) {
        workerDao.deleteWorker(id);
    }

    @Override
    public void deleteWorker(String login) {
        workerDao.deleteWorker(login);
    }

    @Override
    public void updateWorker(int id, Worker worker) throws IncorrectInputException, SQLIntegrityConstraintViolationException, ParseException {
        if (!(Checker.checkLogin(worker.getLogin()) || Checker.checkEmail(worker.getEmail()) ||
                Checker.checkName(worker.getFirstName()) || Checker.checkName(worker.getLastName()) ||
                Checker.checkDate(worker.getBirthdayDate()))) {
            throw new IncorrectInputException("Incorrect login");
        }
        workerDao.updateWorker(id, worker);
        return;
    }
}
