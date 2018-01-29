package hornsandhooves.hornsandhooves.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAnyAttribute;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "executor")
public class Executor extends PersistentModel {

    private String name;
    private Department department;
    private List<Order> orders = new ArrayList<>();

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "department_id",foreignKey = @ForeignKey(name = "fk_executor_department"))
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @ManyToMany(mappedBy = "executors")
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
