package one.digitalinnovation.javaspringbootpersonapi.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    @ApiModelProperty(notes = "Identificador único para produto.")
    private Long id;

    @ApiModelProperty(notes = "Nome do produto entre 2 e 50 caracteres.", required = true)
    @NotEmpty
    @Size(min = 2, max = 50)
    private String name;

    @ApiModelProperty(notes = "Descrição do produto entre 2 e 100 caracteres.", required = true)
    @NotEmpty
    @Size(min = 2, max = 100)
    private String description;

    @ApiModelProperty(notes = "Valor unitário do produto.", required = true)
    @NotNull
    private double value;
}