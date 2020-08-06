package one.digitalinnovation.javaspringbootpersonapi.builder;

import lombok.Builder;
import one.digitalinnovation.javaspringbootpersonapi.dto.request.PhoneDTO;
import one.digitalinnovation.javaspringbootpersonapi.entity.Phone;
import one.digitalinnovation.javaspringbootpersonapi.enums.PhoneType;

@Builder
public class PhoneDTOBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private PhoneType type = PhoneType.MOBILE;

    @Builder.Default
    private String number = "(19)999999999";

    public PhoneDTO toPhoneDTO(){
        return new PhoneDTO(id,type,number);
    }

    public Phone toPhone(){
        return new Phone(id,type,number);
    }
}
