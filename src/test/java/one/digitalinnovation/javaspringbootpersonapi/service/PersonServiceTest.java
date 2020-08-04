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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
    private static final Long INVALID_PERSON_ID = 2L;

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

    @Test
    void whenValidPersonIdIsGivenThenReturnAProduct() throws RecursoNotFoundException {
        // given
        PersonDTO personDTO = PersonDTOBuilder.builder().build().toPersonDTO();
        Person expectedPerson = personMapper.toModel(personDTO);

        // when
        when(personRepository.findById(personDTO.getId())).thenReturn(Optional.of(expectedPerson));

        //then
        PersonDTO foundPersonDTO = personService.findById(personDTO.getId());
        assertThat(foundPersonDTO.getCpf(), is(equalTo(personDTO.getCpf())));
    }

    @Test
    void whenNotRegisteredPersonIdIsGivenThenThrowAnException() {
        // given
        PersonDTO expectedFoundPersonDTO = PersonDTOBuilder.builder().build().toPersonDTO();

        // when
        when(personRepository.findById(expectedFoundPersonDTO.getId())).thenReturn(Optional.empty());

        // then
        assertThrows(RecursoNotFoundException.class, () -> personService.findById(expectedFoundPersonDTO.getId()));
    }

    @Test
    void whenExclusionIsCalledWithValidIdThenAPersonShouldBeDeleted() throws RecursoNotFoundException {
        // given
        PersonDTO personDTO = PersonDTOBuilder.builder().build().toPersonDTO();
        Person expectedPersonDeleted = personMapper.toModel(personDTO);

        // when
        when(personRepository.findById(personDTO.getId())).thenReturn(Optional.of(expectedPersonDeleted));
        doNothing().when(personRepository).deleteById(personDTO.getId());

        // then
        personService.delete(personDTO.getId());
        verify(personRepository, times(1)).findById(personDTO.getId());
        verify(personRepository, times(1)).deleteById(personDTO.getId());
    }

    @Test
    void whenExclusionIsCalledWithInvalidIdThenExceptionShouldBeThrown() {
        // when
        when(personRepository.findById(INVALID_PERSON_ID)).thenReturn(Optional.empty());

        // then
        assertThrows(RecursoNotFoundException.class, ()-> personService.delete(INVALID_PERSON_ID));
    }

    // continuar implementando upddateById
}
