package one.digitalinnovation.javaspringbootpersonapi.service;

import one.digitalinnovation.javaspringbootpersonapi.dto.MessageResponseDTO;
import one.digitalinnovation.javaspringbootpersonapi.dto.request.ProductItemDTO;
import one.digitalinnovation.javaspringbootpersonapi.entity.Product;
import one.digitalinnovation.javaspringbootpersonapi.entity.ProductItem;
import one.digitalinnovation.javaspringbootpersonapi.exception.RecursoNotFoundException;
import one.digitalinnovation.javaspringbootpersonapi.mapper.ProductItemMapper;
import one.digitalinnovation.javaspringbootpersonapi.mapper.ProductMapper;
import one.digitalinnovation.javaspringbootpersonapi.repository.ProductItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductItemService {
    private final ProductMapper productMapper = ProductMapper.INSTANCE;
    private final ProductItemMapper productItemMapper = ProductItemMapper.INSTANCE;
    private ProductItemRepository productItemRepository;

    @Autowired
    public ProductItemService(ProductItemRepository productItemRepository) {
        this.productItemRepository = productItemRepository;
    }

    public MessageResponseDTO createProductItem(ProductItemDTO productItemDTO) {
        Product productToSave = productMapper.toModel(productItemDTO.getProductDTO());
        ProductItem productItemToSave = productItemMapper.toModel(productItemDTO);

        productItemToSave.setProduct(productToSave);
        productItemToSave.setTotalValue(productToSave.getValue() * productItemToSave.getQuantity());
        ProductItem savedProductItem = productItemRepository.save(productItemToSave);

        System.out.println("testando productItemSaved..." + savedProductItem);
        return messageResponse("Created productItem with id ", savedProductItem.getId());
    }

    private MessageResponseDTO messageResponse(String message, Long id) {
        return MessageResponseDTO.builder()
                .message(message + id)
                .build();
    }

    private ProductItem verifyIfExists(Long id) throws RecursoNotFoundException {
        return productItemRepository.findById(id).orElseThrow(()-> new RecursoNotFoundException("ProductItem not found with ID ", id));
    }

    public List<ProductItemDTO> findAll() {
        List<ProductItem> allProductItem = productItemRepository.findAll();
        return allProductItem.stream()
                .map(productItemMapper::toDTO)
                .collect(Collectors.toList());
    }
}
