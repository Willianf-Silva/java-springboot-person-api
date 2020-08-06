package one.digitalinnovation.javaspringbootpersonapi.builder;

import lombok.Builder;
import one.digitalinnovation.javaspringbootpersonapi.dto.request.ProductDTO;
import one.digitalinnovation.javaspringbootpersonapi.dto.request.ProductItemDTO;

@Builder
public class ProductItemDTOBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private int quantity = 2;

    @Builder.Default
    private double totalValue = 0;

    @Builder.Default
    private ProductDTO productDTO = ProductDTOBuilder.builder().build().toProductDTO();

    public ProductItemDTO toProductItemDTO(){
        return new ProductItemDTO(id, quantity, totalValue, productDTO);
    }
}
