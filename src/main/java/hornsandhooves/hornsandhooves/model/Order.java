package hornsandhooves.hornsandhooves.model;

import hornsandhooves.hornsandhooves.converter.ProductionTypeConverter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends PersistentModel {

    private String name;
    private Date dateEnd;
    private ProductionType productionType;
    private List<Executor> executors = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "executor_order_link",
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk_executor_order_link_order")),
            inverseJoinColumns = @JoinColumn(name = "executor_id", referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk_executor_order_link_executor")))
    public List<Executor> getExecutors() {
        return executors;
    }

    public void setExecutors(List<Executor> executors) {
        this.executors = executors;
    }


    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "date_end")
    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    @Column(name = "production_type_id")
    @Convert(converter = ProductionTypeConverter.class)
    public ProductionType getProductionType() {
        return productionType;
    }

    public void setProductionType(ProductionType productionType) {
        this.productionType = productionType;
    }
}
