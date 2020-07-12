package one.digitalinnovation.javaspringbootpersonapi.dto.request;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.digitalinnovation.javaspringbootpersonapi.enums.PhoneType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDTO {

    @ApiModelProperty(notes = "Identificador único para cada telefone.")
    private Long id;

    @ApiModelProperty(notes = "Tipo de telefone que está sendo adicionado," +
            " sendo válido somente os valores (Home), (Mobile) e (Commercial).",
            required = true)
    @Enumerated(EnumType.STRING)
    private PhoneType type;

    @ApiModelProperty(notes = "Numedo do telefone entre 13 e 14 caracteres.",
            required = true)
    @NotEmpty
    @Size(min = 13, max = 14)
    private String number;
}
