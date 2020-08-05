package one.digitalinnovation.javaspringbootpersonapi.controller;

import one.digitalinnovation.javaspringbootpersonapi.builder.PersonDTOBuilder;
import one.digitalinnovation.javaspringbootpersonapi.dto.MessageResponseDTO;
import one.digitalinnovation.javaspringbootpersonapi.dto.request.PersonDTO;
import one.digitalinnovation.javaspringbootpersonapi.exception.RecursoNotFoundException;
import one.digitalinnovation.javaspringbootpersonapi.service.PersonService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({MockitoExtension.class})
public class PersonControllerTest {

    private static final String PERSON_API_URL_PATH = "/api/v1/peoples";
    private static final long INVALID_PERSON_ID = 2L;

    MockMvc mockMvc;

    @Mock
    PersonService personService;

    @InjectMocks
    PersonController personController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(personController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void whenPOSTIsCalledThenAPersonIsCreated() throws Exception {
        // given
        PersonDTO personDTO = PersonDTOBuilder.builder().build().toPersonDTO();

        // when
        MessageResponseDTO expectedSuccessMessageDTO = MessageResponseDTO.builder().message("Created person with id " + personDTO.getId()).build();
        when(personService.createPerson(personDTO)).thenReturn(expectedSuccessMessageDTO);

        //then
        mockMvc.perform(post(PERSON_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(personDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value(expectedSuccessMessageDTO.getMessage()));
    }

    @Test
    void whenPOSTIsCalledWithoutRequiredFieldThenAmErrorIsReturned() throws Exception {
        // given
        PersonDTO personDTO = PersonDTOBuilder.builder().build().toPersonDTO();
        personDTO.setCpf(null);

        //then
        mockMvc.perform(post(PERSON_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(personDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenGETIsCalledWithValidIdThenOkStatusIsReturned() throws Exception {
        // given
        PersonDTO personDTO = PersonDTOBuilder.builder().build().toPersonDTO();

        // when
        when(personService.findById(personDTO.getId())).thenReturn(personDTO);

        // then
        mockMvc.perform(get(PERSON_API_URL_PATH + "/" + personDTO.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                //.andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.firstName").value(personDTO.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(personDTO.getLastName()))
                .andExpect(jsonPath("$.cpf").value(personDTO.getCpf()))
                .andExpect(jsonPath("$.birthDate").value(personDTO.getBirthDate()));
    }

    @Test
    void whenGETIsCalledWithoutRegisteredIdThenNotFoundStatusIsReturned() throws Exception {
        // given
        PersonDTO personDTO = PersonDTOBuilder.builder().build().toPersonDTO();

        // when
        when(personService.findById(personDTO.getId())).thenThrow(RecursoNotFoundException.class);

        // then
        mockMvc.perform(get(PERSON_API_URL_PATH + "/" + personDTO.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenGETListWithPersonIsCalledThenOkStatusIsReturned() throws Exception {
        // given
        PersonDTO personDTO = PersonDTOBuilder.builder().build().toPersonDTO();

        // when
        when(personService.listAll()).thenReturn(Collections.singletonList(personDTO));

        // then
        mockMvc.perform(get(PERSON_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                //.andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$[0].firstName").value(personDTO.getFirstName()))
                .andExpect(jsonPath("$[0].lastName").value(personDTO.getLastName()))
                .andExpect(jsonPath("$[0].cpf").value(personDTO.getCpf()))
                .andExpect(jsonPath("$[0].birthDate").value(personDTO.getBirthDate()));
    }

    @Test
    void whenDELETEIsCalledWithValidIdThenNoContentStatusIsReturned() throws Exception {
        // given
        PersonDTO personDTO = PersonDTOBuilder.builder().build().toPersonDTO();

        // when
        doNothing().when(personService).delete(personDTO.getId());

        // then
        mockMvc.perform(delete(PERSON_API_URL_PATH + "/" + personDTO.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void whenDELETEIsCalledWithInvalidIdThenNotFoundStatusIsReturned() throws Exception {
        // when
        doThrow(RecursoNotFoundException.class).when(personService).delete(INVALID_PERSON_ID);

        // then
        mockMvc.perform(delete(PERSON_API_URL_PATH + "/" + INVALID_PERSON_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenPUTIsCalledThenAPersonIsUpdated() throws Exception {
        // given
        PersonDTO personDTO = PersonDTOBuilder.builder().build().toPersonDTO();

        // when
        MessageResponseDTO expectedSuccessMessageResponseDTO = MessageResponseDTO.builder().message("Update person with id " + personDTO.getId()).build();
        when(personService.upddateById(personDTO.getId(), personDTO)).thenReturn(expectedSuccessMessageResponseDTO);

        // then
        mockMvc.perform(put(PERSON_API_URL_PATH + "/" + personDTO.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(personDTO)))
                .andExpect(status().isOk())
                //.andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.message").value(expectedSuccessMessageResponseDTO.getMessage()));
    }

    @Test
    void whenPUTIsCalledWithoutRequiredFieldThenAmErrorIsReturned() throws Exception {
        // given
        PersonDTO personDTO = PersonDTOBuilder.builder().build().toPersonDTO();
        personDTO.setFirstName(null);

        //then
        mockMvc.perform(put(PERSON_API_URL_PATH + "/" + personDTO.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(personDTO)))
                //.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest());

    }

    @Test
    void whenPUTsCalledWithInvalidIdThenNotFoundStatusIsReturned() throws Exception {
        // given
        PersonDTO personDTO = PersonDTOBuilder.builder().build().toPersonDTO();

        // when
        doThrow(RecursoNotFoundException.class).when(personService).upddateById(INVALID_PERSON_ID, personDTO);

        // then
        mockMvc.perform(put(PERSON_API_URL_PATH + "/" + INVALID_PERSON_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(personDTO)))
                .andExpect(status().isNotFound());
    }
}
