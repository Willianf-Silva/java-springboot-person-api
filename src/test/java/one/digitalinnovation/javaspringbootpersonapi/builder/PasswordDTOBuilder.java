package one.digitalinnovation.javaspringbootpersonapi.builder;

import lombok.Builder;
import one.digitalinnovation.javaspringbootpersonapi.dto.request.AttendantDTO;
import one.digitalinnovation.javaspringbootpersonapi.dto.request.PasswordDTO;
import one.digitalinnovation.javaspringbootpersonapi.dto.request.PersonDTO;
import one.digitalinnovation.javaspringbootpersonapi.dto.request.ProductItemDTO;

import java.util.Collections;
import java.util.List;

@Builder
public class PasswordDTOBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private PersonDTO person = PersonDTOBuilder.builder().build().toPersonDTO();

    @Builder.Default
    private AttendantDTO attendant = AttendantDTOBuilder.builder().build().toAttendantDTO();

    @Builder.Default
    private List<ProductItemDTO> productItems = Collections.singletonList(ProductItemDTOBuilder.builder().build().toProductItemDTO());

    @Builder.Default
    private String date = "08-03-1988";

    @Builder.Default
    private double cost = 0;

    @Builder.Default
    private String status = "Pendente";

    public PasswordDTO toPasswordDTO(){
        return new PasswordDTO(id, person, attendant, productItems, date, cost, status);
    }
}

