package one.digitalinnovation.javaspringbootpersonapi.mapper;

import one.digitalinnovation.javaspringbootpersonapi.dto.request.PasswordDTO;
import one.digitalinnovation.javaspringbootpersonapi.dto.request.ProductItemDTO;
import one.digitalinnovation.javaspringbootpersonapi.entity.Password;
import one.digitalinnovation.javaspringbootpersonapi.entity.ProductItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PasswordMapper {
    PasswordMapper INSTANCE = Mappers.getMapper(PasswordMapper.class);

    @Mapping(target = "date", source = "date", dateFormat = "dd-MM-yyyy")
    Password toModel (PasswordDTO passwordDTO);

    @Mapping(target = "date", source = "date", dateFormat = "dd-MM-yyyy")
    PasswordDTO toDTO (Password password);

    @Mapping(target = "product", source = "productDTO")
    ProductItem toModel(ProductItemDTO productItemDTO);

    @Mapping(target = "productDTO", source = "product")
    ProductItemDTO toDTO(ProductItem productItem);
}
