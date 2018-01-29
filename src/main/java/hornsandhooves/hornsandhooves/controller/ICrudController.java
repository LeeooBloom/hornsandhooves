package hornsandhooves.hornsandhooves.controller;

import hornsandhooves.hornsandhooves.dao.Dao;
import hornsandhooves.hornsandhooves.model.PersistentModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface ICrudController<T extends PersistentModel, D extends Dao<T>> {

    @PutMapping(value = "/insert")
    default ResponseEntity<T> insert(@RequestBody T model) {
        getDao().save(model);
        return new ResponseEntity<>(model, HttpStatus.CREATED);
    }

    @PutMapping(value = "/update")
    default ResponseEntity<T> update(@RequestBody T model) {
        getDao().update(model);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete")
    default ResponseEntity<T> delete(@RequestBody T model) {
        getDao().delete(model);
        return new ResponseEntity<>(model, HttpStatus.ACCEPTED);
    }

    D getDao();
}
