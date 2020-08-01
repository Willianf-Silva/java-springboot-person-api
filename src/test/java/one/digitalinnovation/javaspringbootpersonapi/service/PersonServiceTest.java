package one.digitalinnovation.javaspringbootpersonapi.service;

import one.digitalinnovation.javaspringbootpersonapi.builder.PersonDTOBuilder;
import one.digitalinnovation.javaspringbootpersonapi.dto.MessageResponseDTO;
import one.digitalinnovation.javaspringbootpersonapi.dto.request.PersonDTO;
import one.digitalinnovation.javaspringbootpersonapi.entity.Person;
import one.digitalinnovation.javaspringbootpersonapi.exception.RecursoNotFoundException;
import one.digitalinnovation.javaspringbootpersonapi.mapper.PersonMapper;
import one.digitalinnovation.javaspringbootpersonapi.repository.PersonRepository;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    PersonRepository personRepository;

    PersonMapper personMapper = PersonMapper.INSTANCE;

    @InjectMocks
    PersonService personService;

    @Test
    void whenPersonInformedThenItShouldBeCreated() throws RecursoNotFoundException {
        // given
        PersonDTO personDTO = PersonDTOBuilder.builder().build().toPersonDTO();
        Person expectedSavedPerson = personMapper.toModel(personDTO);

        // when
        when(personRepository.save(expectedSavedPerson)).thenReturn(expectedSavedPerson);

        // then
        MessageResponseDTO expectedSuccessMessageResponseDTO = MessageResponseDTO.builder()
                .message("Created person with id " + personDTO.getId())
                .build();

        MessageResponseDTO successMessageResponseDTO = personService.createPerson(personDTO);

        assertThat(successMessageResponseDTO.getMessage(),
                is(equalTo("Created person with id " + personDTO.getId())));

    }

    @Test
    void whenListPersonIsCalledThenReturnAListOfPerson() {
        // given
        PersonDTO personDTO = PersonDTOBuilder.builder().build().toPersonDTO();
        Person expectedFoundPerson = personMapper.toModel(personDTO);

        // when
        when(personRepository.findAll()).thenReturn(Collections.singletonList(expectedFoundPerson));

        // then
        List<PersonDTO> foundListPersonsDTO = personService.listAll();

        assertThat(foundListPersonsDTO, is(not(empty())));
        assertThat(foundListPersonsDTO.get(0).getCpf(), is(equalTo(personDTO.getCpf())));
    }

    @Test
    void whenListPersonIsCalledThenReturnAEmptyListOfPerson() {
        // when
        when(personRepository.findAll()).thenReturn(Collections.EMPTY_LIST);

        // then
        List<PersonDTO> foundListPersonsDTO = personService.listAll();

        assertThat(foundListPersonsDTO, is(empty()));
    }
}
