package one.digitalinnovation.javaspringbootpersonapi.mapper;

import one.digitalinnovation.javaspringbootpersonapi.dto.request.ProductItemDTO;
import one.digitalinnovation.javaspringbootpersonapi.entity.ProductItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductItemMapper {
    ProductItemMapper INSTANCE = Mappers.getMapper(ProductItemMapper.class);

    @Mapping(target = "product", source = "productDTO")
    ProductItem toModel(ProductItemDTO productItemDTO);

    @Mapping(target = "productDTO", source = "product")
    ProductItemDTO toDTO(ProductItem productItem);
}