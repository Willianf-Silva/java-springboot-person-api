package one.digitalinnovation.javaspringbootpersonapi.builder;

import lombok.Builder;
import one.digitalinnovation.javaspringbootpersonapi.dto.request.AttendantDTO;
import one.digitalinnovation.javaspringbootpersonapi.entity.Phone;

import java.util.Collections;
import java.util.List;

@Builder
public class AttendantDTOBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String firstName = "Willian";

    @Builder.Default
    private String lastName = "Silva";

    @Builder.Default
    private String cpf = "409.251.368-24";

    @Builder.Default
    private String function = "barbeiro";

    @Builder.Default
    private List<Phone> phones = Collections.singletonList(PhoneDTOBuilder.builder().build().toPhone());


    public AttendantDTO toAttendantDTO(){
        return new AttendantDTO(id,firstName,lastName,cpf,function,phones);
    }
}
