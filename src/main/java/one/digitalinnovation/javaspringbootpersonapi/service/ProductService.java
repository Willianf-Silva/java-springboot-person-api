package one.digitalinnovation.javaspringbootpersonapi.service;

import one.digitalinnovation.javaspringbootpersonapi.dto.MessageResponseDTO;
import one.digitalinnovation.javaspringbootpersonapi.dto.request.ProductDTO;
import one.digitalinnovation.javaspringbootpersonapi.entity.Product;
import one.digitalinnovation.javaspringbootpersonapi.mapper.ProductMapper;
import one.digitalinnovation.javaspringbootpersonapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductMapper productMapper = ProductMapper.INSTANCE;
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public MessageResponseDTO createProduct(ProductDTO productDTO) {
        Product savedProduct = productRepository.save(productMapper.toModel(productDTO));
        return messageResponse("Created product with id ", savedProduct.getId());
    }

    private MessageResponseDTO messageResponse(String message, Long id) {
        return MessageResponseDTO.builder()
                .message(message + id)
                .build();
    }
}