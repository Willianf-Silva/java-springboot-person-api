package one.digitalinnovation.javaspringbootpersonapi.mapper;

import one.digitalinnovation.javaspringbootpersonapi.dto.request.AttendantDTO;
import one.digitalinnovation.javaspringbootpersonapi.entity.Attendant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


/*
O Mapper realiza a conversão dos dados recebidos pelo controller (client) no formato DTO para
o formato da entity e passa para a entity tratar as validações do banco de dados.
 */

@Mapper
public interface AttendantMapper {
    AttendantMapper INSTANCE = Mappers.getMapper(AttendantMapper.class);

    Attendant toModel(AttendantDTO attendantDTO);

    AttendantDTO toDTO(Attendant attendant);
}
