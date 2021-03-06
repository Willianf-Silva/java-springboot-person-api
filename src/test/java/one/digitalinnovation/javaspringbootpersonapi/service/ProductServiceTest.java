package one.digitalinnovation.javaspringbootpersonapi.service;

import one.digitalinnovation.javaspringbootpersonapi.builder.ProductDTOBuilder;
import one.digitalinnovation.javaspringbootpersonapi.dto.MessageResponseDTO;
import one.digitalinnovation.javaspringbootpersonapi.dto.request.ProductDTO;
import one.digitalinnovation.javaspringbootpersonapi.entity.Product;
import one.digitalinnovation.javaspringbootpersonapi.exception.ProductAlreadyRegisteredException;
import one.digitalinnovation.javaspringbootpersonapi.exception.RecursoNotFoundException;
import one.digitalinnovation.javaspringbootpersonapi.mapper.ProductMapper;
import one.digitalinnovation.javaspringbootpersonapi.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    private static final long INVALID_PRODUCT_ID = 1L;

    @Mock
    private ProductRepository productRepository;

    private ProductMapper productMapper = ProductMapper.INSTANCE;

    @InjectMocks
    private ProductService productService;

    @Test
    void whenProductInformedThenItShouldBeCreated() throws ProductAlreadyRegisteredException {
        // given
        ProductDTO ProductDTO = ProductDTOBuilder.builder().build().toProductDTO(); //Criando um objeto para simular um post vindo do client
        Product expectedSavedProduct = productMapper.toModel(ProductDTO); // Convertendo o o dto para uma entity

        //when
        when(productRepository.findByName(ProductDTO.getName())).thenReturn(Optional.empty());
        when(productRepository.save(expectedSavedProduct)).thenReturn(expectedSavedProduct); //chamando de fato o método do service para executar a operação de salvar

        //then
        MessageResponseDTO expectedSuccessMessage = MessageResponseDTO.builder()
                .message("Created product with id " + ProductDTO.getId())
                .build();
        MessageResponseDTO successMessageResponseDTO = productService.createProduct(ProductDTO); //armazenando o retorno do processo de salvar

        assertThat(successMessageResponseDTO.getMessage(),
                is(equalTo("Created product with id " + ProductDTO.getId()))); // comparando a mensagem de retorno se é o esperado

        Assertions.assertEquals(expectedSuccessMessage, successMessageResponseDTO);
    }

    @Test
    void whenAlreadyRegisteredProductInformedThenAnExceptionShouldBeThrown(){
        // given
        ProductDTO expectedProductDTO = ProductDTOBuilder.builder().build().toProductDTO(); //Criando um objeto para simular um post vindo do client
        Product duplicatedProduct = productMapper.toModel(expectedProductDTO); // Convertendo o o dto para uma entity

        //when
        when(productRepository.findByName(expectedProductDTO.getName())).thenReturn(Optional.of(duplicatedProduct));

        //then
        assertThrows(ProductAlreadyRegisteredException.class, () -> productService.createProduct(expectedProductDTO)); //o Junit verifica se foi lançado uma exception do tipo ProductAlreadyRegisteredException na hora de executar o create product
    }

    @Test
    void whenValidProductIdIsGivenThenReturnAProduct() throws RecursoNotFoundException {
        // given
        ProductDTO expectedFoundProductDTO = ProductDTOBuilder.builder().build().toProductDTO();
        Product expectedFoundProduct = productMapper.toModel(expectedFoundProductDTO);

        // when
        when(productRepository.findById(expectedFoundProductDTO.getId()))
                .thenReturn(Optional.of(expectedFoundProduct));

        // then
        ProductDTO foundProductDTO = productService.findById(expectedFoundProductDTO.getId());

        assertThat(foundProductDTO, is(equalTo(expectedFoundProductDTO)));
    }

    @Test
    void whenNotRegisteredProductIdIsGivenThenThrowAnException(){
        // given
        ProductDTO expectedFoundProductDTO = ProductDTOBuilder.builder().build().toProductDTO();

        // when
        when(productRepository.findById(expectedFoundProductDTO.getId()))
                .thenReturn(Optional.empty());

        // then
        assertThrows(RecursoNotFoundException.class, () -> productService.findById(expectedFoundProductDTO.getId()));
    }

    @Test
    void whenListProductIsCalledThenReturnAListOfProduct() {
        // given
        ProductDTO expectedFoundProductDTO = ProductDTOBuilder.builder().build().toProductDTO();
        Product expectedFoundProduct = productMapper.toModel(expectedFoundProductDTO);

        // when
        when(productRepository.findAll())
                .thenReturn(Collections.singletonList(expectedFoundProduct));

        // then
        List<ProductDTO> foundListProductsDTO = productService.listAll();

        assertThat(foundListProductsDTO, is(not(empty())));
        assertThat(foundListProductsDTO.get(0), is(equalTo(expectedFoundProductDTO)));
    }

    @Test
    void whenListProductIsCalledThenReturnAEmptyListOfProduct() {
        // when
        when(productRepository.findAll())
                .thenReturn(Collections.EMPTY_LIST);

        // then
        List<ProductDTO> foundListProductsDTO = productService.listAll();

        assertThat(foundListProductsDTO, is(empty()));
    }

    @Test
    void whenExclusionIsCalledWithValidIdThenAProductShouldBeDeleted() throws RecursoNotFoundException {
        // given
        ProductDTO expectedDeletedProductDTO = ProductDTOBuilder.builder().build().toProductDTO();
        Product expectedDeletedProduct = productMapper.toModel(expectedDeletedProductDTO);

        // when
        when(productRepository.findById(expectedDeletedProductDTO.getId()))
                .thenReturn(Optional.of(expectedDeletedProduct));
        doNothing().when(productRepository).deleteById(expectedDeletedProductDTO.getId());

        // then
        productService.delete(expectedDeletedProductDTO.getId());
        verify(productRepository, times(1)).findById(expectedDeletedProductDTO.getId());
        verify(productRepository, times(1)).deleteById(expectedDeletedProductDTO.getId());
    }

    @Test
    void whenExclusionIsCalledWithInvalidIdThenExceptionShouldBeThrown(){
        when(productRepository.findById(INVALID_PRODUCT_ID)).thenReturn(Optional.empty());

        assertThrows(RecursoNotFoundException.class, () -> productService.delete(INVALID_PRODUCT_ID));
    }

    @Test
    void whenProductAndIdInformedThenItShouldBeUpdated() throws RecursoNotFoundException {
        // given
        ProductDTO productDTO = ProductDTOBuilder.builder().build().toProductDTO();
        productDTO.setName("Corte e barba");
        Product expectedSavedProduct = productMapper.toModel(productDTO);

        // when
        when(productRepository.findById(productDTO.getId())).thenReturn(Optional.of(expectedSavedProduct));
        when(productRepository.save(expectedSavedProduct)).thenReturn(expectedSavedProduct);

        // then
        MessageResponseDTO expectedSuccessMessage = MessageResponseDTO.builder().message("Update product with id " + productDTO.getId()).build();
        MessageResponseDTO successMessageResponseDTO = productService.updateProduct(productDTO.getId(), productDTO);

        assertThat(successMessageResponseDTO.getMessage(), is(equalTo("Update product with id " + productDTO.getId())));
        Assertions.assertEquals(expectedSuccessMessage, successMessageResponseDTO);
    }

    @Test
    void whenNotRegisteredProductIdIsGivenForUpdateThenThrowAnException() {
     // given
     ProductDTO expectedProductDTO = ProductDTOBuilder.builder().build().toProductDTO();

     // when
        when(productRepository.findById(expectedProductDTO.getId())).thenReturn(Optional.empty());

     // then
        assertThrows(RecursoNotFoundException.class, () -> productService.updateProduct(expectedProductDTO.getId(), expectedProductDTO));
    }
}
