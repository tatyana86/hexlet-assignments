package exercise.mapper;

// BEGIN
import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.model.Product;
import org.mapstruct.*;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ProductMapper {

    //логика следующая:
    //при возвращении Product в таргет помещаются поля класса Product, а в источник поля класса ProductDTO
    @Mapping(target = "cost", source = "price")
    @Mapping(target = "barcode", source = "vendorCode")
    @Mapping(target = "name", source = "title")
    public abstract Product map(ProductCreateDTO dto);

    //здесь обратная логика
    @Mapping(target = "price", source = "cost")
    @Mapping(target = "vendorCode", source = "barcode")
    @Mapping(target = "title", source = "name")
    public abstract ProductDTO map(Product model);

    @Mapping(target = "cost", source = "price")
    public abstract void update(ProductUpdateDTO dto, @MappingTarget Product model);
}
// END
