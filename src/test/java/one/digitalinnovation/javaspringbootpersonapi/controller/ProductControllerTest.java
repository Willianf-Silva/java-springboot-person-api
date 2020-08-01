package one.digitalinnovation.javaspringbootpersonapi.controller;

import one.digitalinnovation.javaspringbootpersonapi.builder.ProductDTOBuilder;
import one.digitalinnovation.javaspringbootpersonapi.dto.MessageResponseDTO;
import one.digitalinnovation.javaspringbootpersonapi.dto.request.ProductDTO;
import one.digitalinnovation.javaspringbootpersonapi.exception.RecursoNotFoundException;
import one.digitalinnovation.javaspringbootpersonapi.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Collections;

import static one.digitalinnovation.javaspringbootpersonapi.utils.JsonConvertionUtils.asJsonString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {
    private static final String PRODUCT_API_URL_PATH = "/api/v1/product";
    private static final long VALID_PRODUCT_ID = 1L;
    private static final long INVALID_PRODUCT_ID = 2L;


    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    ProductController productController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void whenPOSTIsCalledThenAProductIsCreated() throws Exception {
        //given
        ProductDTO productDTO = ProductDTOBuilder.builder().build().toProductDTO();

        //when
        when(productService.createProduct(productDTO)).thenReturn(MessageResponseDTO.builder().message("Created product with id " + productDTO.getId()).build());

        //then
        mockMvc.perform(post(PRODUCT_API_URL_PATH)
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(productDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    void whenPOSTIsCalledWithoutRequiredFieldThenAmErrorIsReturned() throws Exception {
        //given
        ProductDTO productDTO = ProductDTOBuilder.builder().build().toProductDTO();
        productDTO.setName(null);

        //then
        mockMvc.perform(post(PRODUCT_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(productDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenGETIsCalledWithValidIdThenOkStatusIsReturned() throws Exception {
        // given
        ProductDTO productDTO = ProductDTOBuilder.builder().build().toProductDTO();

        //when
        when(productService.findById(productDTO.getId()))
                .thenReturn(productDTO);

        //then
        mockMvc.perform(get(PRODUCT_API_URL_PATH + "/" + productDTO.getId())
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    }

    @Test
    void whenGETIsCalledWithoutRegisteredIdThenNotFoundStatusIsReturned() throws Exception {
        // given
        ProductDTO productDTO = ProductDTOBuilder.builder().build().toProductDTO();

        //when
        when(productService.findById(productDTO.getId()))
                .thenThrow(RecursoNotFoundException.class);

        //then
        mockMvc.perform(get(PRODUCT_API_URL_PATH + "/" + productDTO.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenGETListWithProductIsCalledThenOkStatusIsReturned() throws Exception {
        // given
        ProductDTO productDTO = ProductDTOBuilder.builder().build().toProductDTO();

        // when
        when(productService.listAll())
                .thenReturn(Collections.singletonList(productDTO));

        //then
        mockMvc.perform(get(PRODUCT_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void whenDELETEIsCalledWithValidIdThenNoContentStatusIsReturned() throws Exception {
        // given
        ProductDTO productDTO = ProductDTOBuilder.builder().build().toProductDTO();

        // when
        doNothing().when(productService).delete(productDTO.getId());

        // then
        mockMvc.perform(delete(PRODUCT_API_URL_PATH + "/" + productDTO.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void whenDELETEIsCalledWithInvalidIdThenNotFoundStatusIsReturned() throws Exception {
        // when
        doThrow(RecursoNotFoundException.class).when(productService).delete(INVALID_PRODUCT_ID);

        // then
        mockMvc.perform(delete(PRODUCT_API_URL_PATH + "/" + INVALID_PRODUCT_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    @Test
    void whenPUTIsCalledThenAProductIsUpdated() throws Exception {
        // given
        ProductDTO productDTO = ProductDTOBuilder.builder().build().toProductDTO();

        // when
        MessageResponseDTO expectedSuccessMessageResponseDTO = MessageResponseDTO.builder().message("Update product with id " + productDTO.getId()).build();
        when(productService.updateProduct(productDTO.getId(), productDTO)).thenReturn(expectedSuccessMessageResponseDTO);

        // then
        mockMvc.perform(put(PRODUCT_API_URL_PATH + "/" + productDTO.getId())
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(productDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void whenPUTIsCalledWithoutRequiredFieldThenAmErrorIsReturned() throws Exception {
        //given
        ProductDTO productDTO = ProductDTOBuilder.builder().build().toProductDTO();
        productDTO.setName(null);

        //then
        mockMvc.perform(put(PRODUCT_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(productDTO)))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    void whenPUTsCalledWithInvalidIdThenNotFoundStatusIsReturned() throws Exception {
        // given
        ProductDTO productDTO = ProductDTOBuilder.builder().build().toProductDTO();

        // when
        doThrow(RecursoNotFoundException.class).when(productService).updateProduct(INVALID_PRODUCT_ID, productDTO);

        // then
        mockMvc.perform(put(PRODUCT_API_URL_PATH + "/" + INVALID_PRODUCT_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(productDTO)))
                .andExpect(status().isNotFound());
    }
}
