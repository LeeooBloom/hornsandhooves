package hornsandhooves.hornsandhooves.controller;

import hornsandhooves.hornsandhooves.dao.OrderDao;
import hornsandhooves.hornsandhooves.dto.DaysAndHoursDto;
import hornsandhooves.hornsandhooves.model.Department;
import hornsandhooves.hornsandhooves.model.Executor;
import hornsandhooves.hornsandhooves.model.Order;
import hornsandhooves.hornsandhooves.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "/order")
public class OrderController implements ICrudController<Order, OrderDao> {

    private final OrderDao orderDao;
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderDao orderDao, OrderService orderService) {
        this.orderDao = orderDao;
        this.orderService = orderService;
    }

    @Override
    @PutMapping(value = "/update")
    public ResponseEntity<Order> insert(Order model) {
        final Order order = orderService.createOrderAndSetExecutor(model);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }


    @GetMapping(value = "/all")
    public ResponseEntity<List<Order>> getAll() {
        final List<Order> all = orderDao.getAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping(value = "/uncompleted")
    public ResponseEntity<List<Order>> getAllUncompleted() {
        final List<Order> allUncompleted = orderDao.getAllUncompleted();
        return new ResponseEntity<>(allUncompleted, HttpStatus.OK);
    }

    @GetMapping(value = "/by_department")
    public ResponseEntity<List<Order>> getAllByDepartment(@RequestBody Department department) {
        final List<Order> allByDepartment = orderService.getByDepartment(department);
        return new ResponseEntity<>(allByDepartment, HttpStatus.OK);
    }

    @GetMapping(value = "/by_executors")
    public ResponseEntity<List<Order>> getAllByExecutors(@RequestBody List<Executor> executors){
        final List<Order> allByExecutors = orderService.getByExecutors(executors);
        return new ResponseEntity<>(allByExecutors, HttpStatus.OK);
    }

    @GetMapping(value = "/time_to_comlete")
    public ResponseEntity<DaysAndHoursDto> getTimeToComplete(@RequestBody Order order){
        return new ResponseEntity<>(orderService.evaluateTimeToComplete(order), HttpStatus.OK);
    }

    @Override
    public OrderDao getDao() {
        return orderDao;
    }
}
