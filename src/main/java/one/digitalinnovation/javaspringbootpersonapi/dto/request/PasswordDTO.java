package one.digitalinnovation.javaspringbootpersonapi.dto.request;

import io.swagger.annotations.ApiModelProperty;
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

        @ApiModelProperty(notes = "Identificador único para a senha.")
        private Long id;

        @ApiModelProperty(notes = "Cliente que está solicitando a senha.",
                required = true)
        @Valid
        private PersonDTO person;

        @ApiModelProperty(notes = "Atendente que realizara os serviços solicitados.",
                required = true)
        @Valid
        private AttendantDTO attendant;

        @ApiModelProperty(notes = "Lista de itens referente a essa senha de atendimento.",
                required = true)
        @Valid
        private List<ProductItemDTO> productItems;

        @ApiModelProperty(notes = "Data que está sendo solicitado a senha de atendimento.",
                required = true)
        private String date;

        @ApiModelProperty(notes = "Valor total dos produtos e ou serviços.")
        private double cost;

        @ApiModelProperty(notes = "Situação da senha de atendimento, " +
                "sendo possível atribuir apenas o status, Pendente ou Concluído.",
                required = true)
        @NotEmpty
        private String status;
    }
