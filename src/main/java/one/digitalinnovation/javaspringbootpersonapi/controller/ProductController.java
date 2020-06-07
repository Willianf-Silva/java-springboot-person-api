package one.digitalinnovation.javaspringbootpersonapi.controller;

import one.digitalinnovation.javaspringbootpersonapi.dto.MessageResponseDTO;
import one.digitalinnovation.javaspringbootpersonapi.dto.request.ProductDTO;
import one.digitalinnovation.javaspringbootpersonapi.entity.Product;
import one.digitalinnovation.javaspringbootpersonapi.exception.RecursoNotFoundException;
import one.digitalinnovation.javaspringbootpersonapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public MessageResponseDTO createProduct(@RequestBody ProductDTO productDTO){
        return productService.createProduct(productDTO);
    }

    @GetMapping
    public List<Product> listAll(){
        return productService.listAll();
    }

    @GetMapping("/{id}")
    public ProductDTO findById(@PathVariable Long id) throws RecursoNotFoundException {
        return productService.findById(id);
    }
}
