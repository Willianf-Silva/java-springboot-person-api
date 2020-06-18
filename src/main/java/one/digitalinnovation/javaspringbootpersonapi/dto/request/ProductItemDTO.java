package one.digitalinnovation.javaspringbootpersonapi.dto.request;

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

    private Long id;

    @NotNull
    private int quantity;

    @NotNull
    private double totalValue;

    @Valid
    private ProductDTO productDTO;
}
