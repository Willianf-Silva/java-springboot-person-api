package one.digitalinnovation.javaspringbootpersonapi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import one.digitalinnovation.javaspringbootpersonapi.dto.MessageResponseDTO;
import one.digitalinnovation.javaspringbootpersonapi.dto.request.ProductItemDTO;
import one.digitalinnovation.javaspringbootpersonapi.exception.RecursoNotFoundException;
import one.digitalinnovation.javaspringbootpersonapi.service.ProductItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "API REST ProdutoItem")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/productItens")
public class ProductItemController {
    private ProductItemService productItemService;

    @Autowired
    public ProductItemController(ProductItemService productItemService) {
        this.productItemService = productItemService;
    }

    @ApiOperation(value = "Incluir produto item.",
    notes = "Inclui um novo item no banco de dados.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createProductItem(@RequestBody @Valid ProductItemDTO productItemDTO){
        return productItemService.createProductItem(productItemDTO);
    }

    @ApiOperation(value = "Retorna único produto item.",
    notes = "Retorna um item único de acordo com o id fornecido.")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductItemDTO findById(@PathVariable Long id) throws RecursoNotFoundException {
        return productItemService.findById(id);
    }

    @ApiOperation(value = "Retorna lista de produto item.",
    notes = "Retorna uma lista com todos os itens cadastrados no banco de dados.")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductItemDTO> findAll(){
        return productItemService.findAll();
    }


    @ApiOperation(value = "Atualiza dados do produto item.",
    notes = "Atualiza o item no banco de dados de acordo com o id fornecido.")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDTO updateProductItem(@PathVariable Long id, @RequestBody @Valid ProductItemDTO productItemDTO) throws RecursoNotFoundException {
        return productItemService.updateProductItem(id, productItemDTO);
    }

    @ApiOperation(value = "Deleta produto item.",
    notes = "Deleta do banco de dados o item com o id fornecido.")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void deleteProductItem(@PathVariable Long id) throws RecursoNotFoundException {
        productItemService.deleteProductItem(id);
    }
}
