package hornsandhooves.hornsandhooves.model;

import hornsandhooves.hornsandhooves.converter.ProductionTypeConverter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "department")
public class Department extends PersistentModel{

    private String name;
    private ProductionType productionType;
    private List<Executor> executors;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "department", cascade = CascadeType.ALL)
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


    @Column(name = "production_type_id")
    @Convert(converter = ProductionTypeConverter.class)
    public ProductionType getProductionType() {
        return productionType;
    }

    public void setProductionType(ProductionType productionType) {
        this.productionType = productionType;
    }
}
