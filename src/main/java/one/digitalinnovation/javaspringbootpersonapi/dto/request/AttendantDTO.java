package one.digitalinnovation.javaspringbootpersonapi.dto.request;

import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(
            notes = "Identificador único para cada atendente.")
    private Long id;

    @ApiModelProperty(
            notes = "Nome do atendente contendo entre 2 e 50 caracteres.",
            required = true
    )
    @NotEmpty
    @Size(min = 2, max = 50, message = "First name there are limit of 50 character.")
    private String firstName;

    @ApiModelProperty(
            notes = "Último nome do atendente contendo entre 2 e 50 caracteres.",
            required = true
    )
    @NotEmpty
    @Size(min = 2, max = 50, message = "Last name there are limit of 50 character.")
    private String lastName;

    @ApiModelProperty(
            notes = "CPF válido do atendente.",
            required = true
    )
    @NotEmpty
    @CPF
    private String cpf;

    @ApiModelProperty(
            notes = "Função do atendente contendo entre 2 e 20 caracteres",
            required = true
    )
    @NotEmpty
    @Size(min = 2, max = 20, message = "Function there are limit of 20 character.")
    private String function;

    @ApiModelProperty(
            notes = "Numedo do telefone cadastrado no banco de dados.",
            required = true
    )
    @Valid
    @NotEmpty
    private List<Phone> phones;
}
