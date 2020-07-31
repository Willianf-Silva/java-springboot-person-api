package one.digitalinnovation.javaspringbootpersonapi.builder;

import lombok.Builder;
import one.digitalinnovation.javaspringbootpersonapi.dto.request.ProductDTO;

@Builder
public class ProductDTOBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String name = "Barba";

    @Builder.Default
    private String description = "Fazer a barba.";

    @Builder.Default
    private double value = 10.99;

    public ProductDTO toProductDTO(){
        return new ProductDTO(id,name,description,value);
    }

}
