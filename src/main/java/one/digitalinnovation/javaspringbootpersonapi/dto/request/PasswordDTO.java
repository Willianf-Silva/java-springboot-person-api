package one.digitalinnovation.javaspringbootpersonapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PasswordDTO {

        private Long id;

        @Valid
        private PersonDTO person;

        @Valid
        private AttendantDTO attendant;

        @Valid
        private List<ProductItemDTO> productItems;


        private String date;

        private double cost;

        @NotEmpty
        private String status;
    }
