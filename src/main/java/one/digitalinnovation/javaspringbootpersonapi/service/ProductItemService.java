package one.digitalinnovation.javaspringbootpersonapi.service;

import one.digitalinnovation.javaspringbootpersonapi.dto.MessageResponseDTO;
import one.digitalinnovation.javaspringbootpersonapi.dto.request.ProductItemDTO;
import one.digitalinnovation.javaspringbootpersonapi.entity.ProductItem;
import one.digitalinnovation.javaspringbootpersonapi.exception.RecursoNotFoundException;
import one.digitalinnovation.javaspringbootpersonapi.mapper.ProductItemMapper;
import one.digitalinnovation.javaspringbootpersonapi.repository.ProductItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductItemService {
    private final ProductItemMapper productItemMapper = ProductItemMapper.INSTANCE;
    private ProductItemRepository productItemRepository;

    @Autowired
    public ProductItemService(ProductItemRepository productItemRepository) {
        this.productItemRepository = productItemRepository;
    }

    public MessageResponseDTO createProductItem(ProductItemDTO productItemDTO) {
        ProductItem savedProductItem = null;

        if (productItemDTO.getQuantity() > 0) {
            productItemDTO.setTotalValue(
                    productItemDTO.getProductDTO().getValue() * productItemDTO.getQuantity()
            );

            savedProductItem = productItemRepository.save(productItemMapper.toModel(productItemDTO));
        }

        return messageResponse("Created product item with id ", savedProductItem.getId());
    }


    public List<ProductItemDTO> findAll() {
        List<ProductItem> allProductItem = productItemRepository.findAll();

        return allProductItem.stream()
                .map(productItemMapper::toDTO)
                .collect(Collectors.toList());
    }


    public ProductItemDTO findById(Long id) throws RecursoNotFoundException {
        Optional<ProductItem> optionalProductItem = productItemRepository.findById(id);

        if (optionalProductItem.isEmpty()){
            throw new RecursoNotFoundException("Product item not found with ID: ", id);
        }return productItemMapper.toDTO(optionalProductItem.get());
    }

    public MessageResponseDTO updateProductItem(Long id, ProductItemDTO productItemDTO) throws RecursoNotFoundException {
        verifyIfExists(id); // verificando se existe no banco de dados o id passado no  Path
        verifyIfExists(productItemDTO.getId()); //Verificando se existe no banco de dados o id passado no Body
        ProductItem updatedProductItem = null;

        if (productItemDTO.getQuantity() > 0) {
            productItemDTO.setTotalValue(
                    productItemDTO.getProductDTO().getValue() * productItemDTO.getQuantity()
            );

            updatedProductItem = productItemRepository.save(productItemMapper.toModel(productItemDTO));
        }
        return messageResponse("Update product item with id ", updatedProductItem.getId());
    }

    public void deleteProductItem(Long id) throws RecursoNotFoundException {
        verifyIfExists(id);
        productItemRepository.deleteById(id);
    }

    private MessageResponseDTO messageResponse(String message, Long id) {
        return MessageResponseDTO.builder()
                .message(message + id)
                .build();
    }

    private ProductItem verifyIfExists(Long id) throws RecursoNotFoundException {
        return productItemRepository.findById(id).orElseThrow(()-> new RecursoNotFoundException("Product item not found with ID ", id));
    }


}
