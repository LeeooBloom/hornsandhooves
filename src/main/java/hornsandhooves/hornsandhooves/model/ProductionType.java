package hornsandhooves.hornsandhooves.model;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Optional;
import java.util.stream.Stream;

public enum  ProductionType {

    SOFT_FURNITURE(1L,"SOFT_FURNITURE"),
    STORING_FURNITURE(2L, "STORING_FURNITURE"),
    OFFICE_FURNITURE(3L,"OFFICE_FURNITURE");

    private long id;
    private String value;


    ProductionType(long id, String value) {
        this.id = id;
        this.value = value;
    }

    static public ProductionType valueOf(long id){
        final Optional<ProductionType> optionalValue = Stream.of(values())
                .filter(productionType -> productionType.getId() == id)
                .findAny();
        if(optionalValue.isPresent())
            return optionalValue.get();
        else
            throw new RuntimeException("ProductionType with id=" + id + " is not present");
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
