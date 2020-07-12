package one.digitalinnovation.javaspringbootpersonapi.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {

    @ApiModelProperty(notes = "Identificador único para cada cliente.")
    private Long id;

    @ApiModelProperty(notes = "Nome do cliente contendo entre 2 e 100 caracteres.",
            required = true)
    @NotEmpty
    @Size(min = 2, max = 100)
    private String firstName;

    @ApiModelProperty(notes = "Último nome do cliente contendo entre 2 e 100 caracteres.",
            required = true)
    @NotEmpty
    @Size(min = 2, max = 100)
    private String lastName;

    @ApiModelProperty(notes = "CPF válido do cliente.",
            required = true)
    @NotEmpty
    @CPF
    private String cpf;

    @ApiModelProperty(notes = "Data de aniverário do cliente.")
    private String birthDate;

    @ApiModelProperty(notes = "Numedo do telefone cadastrado no banco de dados.",
            required = true)
    @Valid
    @NotEmpty
    private List<PhoneDTO> phones;
}
