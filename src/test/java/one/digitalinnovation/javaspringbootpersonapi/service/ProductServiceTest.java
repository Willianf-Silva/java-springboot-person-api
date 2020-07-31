package one.digitalinnovation.javaspringbootpersonapi.service;

import one.digitalinnovation.javaspringbootpersonapi.builder.ProductDTOBuilder;
import one.digitalinnovation.javaspringbootpersonapi.dto.MessageResponseDTO;
import one.digitalinnovation.javaspringbootpersonapi.dto.request.ProductDTO;
import one.digitalinnovation.javaspringbootpersonapi.entity.Product;
import one.digitalinnovation.javaspringbootpersonapi.exception.ProductAlreadyRegisteredException;
import one.digitalinnovation.javaspringbootpersonapi.exception.RecursoNotFoundException;
import one.digitalinnovation.javaspringbootpersonapi.mapper.ProductMapper;
import one.digitalinnovation.javaspringbootpersonapi.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    private ProductMapper productMapper = ProductMapper.INSTANCE;

    @InjectMocks
    private ProductService productService;

    @Test
    void whenProductInformedThenItShouldBeCreated() throws ProductAlreadyRegisteredException {
        // given
        ProductDTO expectedProductDTO = ProductDTOBuilder.builder().build().toProductDTO(); //Criando um objeto para simular um post vindo do client
        Product expectedSavedBeer = productMapper.toModel(expectedProductDTO); // Convertendo o o dto para uma entity

        //when
        when(productRepository.findByName(expectedProductDTO.getName())).thenReturn(Optional.empty());
        when(productRepository.save(expectedSavedBeer)).thenReturn(expectedSavedBeer); //chamando de fato o método do service para executar a operação de salvar

        //then
        MessageResponseDTO createdProductDTO = productService.createProduct(expectedProductDTO); //armazenando o retorno do processo de salvar

        assertThat(createdProductDTO.getMessage(),
                is(equalTo("Created product with id " + expectedProductDTO.getId()))); // comparando a mensagem de retorno se é o esperado
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
}
