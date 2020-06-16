package one.digitalinnovation.javaspringbootpersonapi.controller;

import one.digitalinnovation.javaspringbootpersonapi.dto.MessageResponseDTO;
import one.digitalinnovation.javaspringbootpersonapi.dto.request.ProductItemDTO;
import one.digitalinnovation.javaspringbootpersonapi.service.ProductItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/productItem")
public class ProductItemController {
    private ProductItemService productItemService;

    @Autowired
    public ProductItemController(ProductItemService productItemService) {
        this.productItemService = productItemService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createProductItem(@RequestBody @Valid ProductItemDTO productItemDTO){
        return productItemService.createProductItem(productItemDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductItemDTO> findAll(){
        return productItemService.findAll();
    }
}
