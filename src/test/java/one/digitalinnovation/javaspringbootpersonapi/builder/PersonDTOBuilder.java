package one.digitalinnovation.javaspringbootpersonapi.builder;

import lombok.Builder;
import one.digitalinnovation.javaspringbootpersonapi.dto.request.PersonDTO;
import one.digitalinnovation.javaspringbootpersonapi.dto.request.PhoneDTO;

import java.util.Collections;
import java.util.List;

@Builder
public class PersonDTOBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String firstName = "Bruno";

    @Builder.Default
    private String lastName = "Silva";

    @Builder.Default
    private String cpf = "369.333.878-79";

    @Builder.Default
    private String birthDate = "23-03-1988";

    @Builder.Default
    private List<PhoneDTO> phones = Collections.singletonList(PhoneDTOBuilder.builder().build().toPhoneDTO());

    public PersonDTO toPersonDTO(){
        return new PersonDTO(id,firstName,lastName,cpf,birthDate,phones);
    }
}
