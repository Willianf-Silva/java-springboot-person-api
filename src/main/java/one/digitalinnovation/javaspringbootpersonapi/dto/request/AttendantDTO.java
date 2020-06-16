package one.digitalinnovation.javaspringbootpersonapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.digitalinnovation.javaspringbootpersonapi.entity.Phone;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

/*
O DTO (Data Transfer Object ) é o mapeamento de dados que realiza a validação quando for passado pelo
client por meio da controller e depois passa para o mapper.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttendantDTO {

    private Long id;

    @NotEmpty
    @Size(min = 2, max = 50, message = "First name there are limit of 50 character.")
    private String firstName;

    @NotEmpty
    @Size(min = 2, max = 50, message = "Last name there are limit of 50 character.")
    private String lastName;

    @NotEmpty
    @CPF
    private String cpf;

    @NotEmpty
    @Size(min = 2, max = 20, message = "Function there are limit of 20 character.")
    private String function;


    @Valid
    @NotEmpty
    private List<Phone> phones;
}
