package one.digitalinnovation.javaspringbootpersonapi.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductItemDTO {

    @ApiModelProperty(notes = "Identificador único para cada item.")
    private Long id;

    @ApiModelProperty(notes = "Quantidade de produto que será adquirido.",
            required = true)
    @NotNull
    private int quantity;

    @ApiModelProperty(notes = "Valor total do item.",
            required = true)
    @NotNull
    private double totalValue;

    @ApiModelProperty(notes = "Produto que está sendo adquirido. Necessário existir no banco de dados.",
            required = true)
    @Valid
    private ProductDTO productDTO;
}
