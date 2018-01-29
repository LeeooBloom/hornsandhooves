package hornsandhooves.hornsandhooves.controller;

import hornsandhooves.hornsandhooves.dao.ExecutorDao;
import hornsandhooves.hornsandhooves.model.Executor;
import hornsandhooves.hornsandhooves.service.ExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/executor")
public class ExecutorController implements ICrudController<Executor, ExecutorDao> {

    private final ExecutorDao executorDao;
    private final ExecutorService executorService;

    @Autowired
    public ExecutorController(ExecutorDao executorDao, ExecutorService executorService) {
        this.executorDao = executorDao;
        this.executorService = executorService;
    }

    @Override
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Executor> delete(Executor model) {
        final Executor executor = executorService.deleteExecutorAndResetOrders(model);
        return new ResponseEntity<>(executor, HttpStatus.OK);
    }

    @Override
    public ExecutorDao getDao() {
        return executorDao;
    }
}
