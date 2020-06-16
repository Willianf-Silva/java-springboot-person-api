package one.digitalinnovation.javaspringbootpersonapi.mapper;

import one.digitalinnovation.javaspringbootpersonapi.dto.request.ProductItemDTO;
import one.digitalinnovation.javaspringbootpersonapi.entity.ProductItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductItemMapper {
    ProductItemMapper INSTANCE = Mappers.getMapper(ProductItemMapper.class);

    ProductItem toModel(ProductItemDTO productItemDTO);

    ProductItemDTO toDTO(ProductItem productItem);
}
