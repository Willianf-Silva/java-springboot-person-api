package one.digitalinnovation.javaspringbootpersonapi.controller;

import one.digitalinnovation.javaspringbootpersonapi.dto.MessageResponseDTO;
import one.digitalinnovation.javaspringbootpersonapi.dto.request.ProductItemDTO;
import one.digitalinnovation.javaspringbootpersonapi.exception.RecursoNotFoundException;
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

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductItemDTO findById(@PathVariable Long id) throws RecursoNotFoundException {
        return productItemService.findById(id);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductItemDTO> findAll(){
        return productItemService.findAll();
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDTO updateProductItem(@PathVariable Long id, @RequestBody @Valid ProductItemDTO productItemDTO) throws RecursoNotFoundException {
        return productItemService.updateProductItem(id, productItemDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void deleteProductItem(@PathVariable Long id) throws RecursoNotFoundException {
        productItemService.deleteProductItem(id);
    }
}
