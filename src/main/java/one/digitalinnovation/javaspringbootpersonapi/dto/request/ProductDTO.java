package one.digitalinnovation.javaspringbootpersonapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long id;

    @NotEmpty
    @Size(min = 2, max = 50)
    private String name;

    @NotEmpty
    @Size(min = 2, max = 100)
    private String description;

    @Valid
    @NotNull
    private double value;
}
