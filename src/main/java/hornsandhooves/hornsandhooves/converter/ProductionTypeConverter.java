package hornsandhooves.hornsandhooves.converter;

import hornsandhooves.hornsandhooves.model.ProductionType;

import javax.persistence.AttributeConverter;

public class ProductionTypeConverter implements AttributeConverter<ProductionType, Long> {
    @Override
    public Long convertToDatabaseColumn(ProductionType attribute) {
        return attribute.getId();
    }

    @Override
    public ProductionType convertToEntityAttribute(Long dbData) {
        return ProductionType.valueOf(dbData);
    }
}
