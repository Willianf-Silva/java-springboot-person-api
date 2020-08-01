package one.digitalinnovation.javaspringbootpersonapi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import one.digitalinnovation.javaspringbootpersonapi.dto.MessageResponseDTO;
import one.digitalinnovation.javaspringbootpersonapi.dto.request.ProductDTO;
import one.digitalinnovation.javaspringbootpersonapi.exception.ProductAlreadyRegisteredException;
import one.digitalinnovation.javaspringbootpersonapi.exception.RecursoNotFoundException;
import one.digitalinnovation.javaspringbootpersonapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "API REST Produtos")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Incluir produto",
            notes = "Inclui um novo produto no banco de dados")
    public MessageResponseDTO createProduct(@RequestBody @Valid ProductDTO productDTO) throws ProductAlreadyRegisteredException {
        return productService.createProduct(productDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retorna lista de produtos.",
            notes = "Retorna uma lista com todos os produtos cadastrados no banco de dados.")
    public List<ProductDTO> listAll(){
        return productService.listAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retorna único produto",
            notes = "Retorna um produto único de acordo com o id fornecido")
    public ProductDTO findById(@PathVariable Long id) throws RecursoNotFoundException {
        return productService.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Atualiza dados de produto",
            notes = "Atualiza um produto no banco de dados de acordo com o id fornecido")
    public MessageResponseDTO updateProduct(@PathVariable Long id, @RequestBody @Valid ProductDTO productDTO) throws RecursoNotFoundException {
        return productService.updateProduct(id, productDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Deleta produto",
            notes = "Deleta do banco de dados o produto com o id fornecido")
    public void delete(@PathVariable Long id) throws RecursoNotFoundException {
        productService.delete(id);
    }
}