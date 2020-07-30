package one.digitalinnovation.javaspringbootpersonapi.service;

import one.digitalinnovation.javaspringbootpersonapi.dto.MessageResponseDTO;
import one.digitalinnovation.javaspringbootpersonapi.dto.request.ProductDTO;
import one.digitalinnovation.javaspringbootpersonapi.entity.Product;
import one.digitalinnovation.javaspringbootpersonapi.exception.ProductAlreadyRegisteredException;
import one.digitalinnovation.javaspringbootpersonapi.exception.RecursoNotFoundException;
import one.digitalinnovation.javaspringbootpersonapi.mapper.ProductMapper;
import one.digitalinnovation.javaspringbootpersonapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductMapper productMapper = ProductMapper.INSTANCE;
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public MessageResponseDTO createProduct(ProductDTO productDTO) throws ProductAlreadyRegisteredException {
        verifyIfIsAlreadyRegistered(productDTO.getName().toString());
        Product savedProduct = productRepository.save(productMapper.toModel(productDTO));
        return messageResponse("Created product with id ", savedProduct.getId());
    }


    public List<Product> listAll() {
        return productRepository.findAll();
    }

    public ProductDTO findById(Long id) throws RecursoNotFoundException {
        verifyIfExists(id);
        return productMapper.toDTO(productRepository.findById(id).get());
    }

    public MessageResponseDTO updateProduct(Long id, ProductDTO productDTO) throws RecursoNotFoundException {
        verifyIfExists(id);
        Product savedProduct = productRepository.save(productMapper.toModel(productDTO));

        return messageResponse("Update product with id ", savedProduct.getId());
    }

    public void delete(Long id) throws RecursoNotFoundException {
        verifyIfExists(id);
        productRepository.deleteById(id);
    }

    private MessageResponseDTO messageResponse(String message, Long id) {
        return MessageResponseDTO.builder()
                .message(message + id)
                .build();
    }

    private Product verifyIfExists(Long id) throws RecursoNotFoundException {
        return productRepository.findById(id).orElseThrow(()-> new RecursoNotFoundException("Product not found with ID ", id));
    }

    private void verifyIfIsAlreadyRegistered(String name) throws ProductAlreadyRegisteredException {
        Optional<Product> optSavedProduct = productRepository.findByName(name);
        if (optSavedProduct.isPresent()){
            throw new ProductAlreadyRegisteredException(name);
        }
    }

}