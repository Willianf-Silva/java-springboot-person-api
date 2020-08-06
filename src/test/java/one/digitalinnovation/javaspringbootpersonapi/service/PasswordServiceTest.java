package one.digitalinnovation.javaspringbootpersonapi.service;

import one.digitalinnovation.javaspringbootpersonapi.builder.PasswordDTOBuilder;
import one.digitalinnovation.javaspringbootpersonapi.builder.PersonDTOBuilder;
import one.digitalinnovation.javaspringbootpersonapi.dto.MessageResponseDTO;
import one.digitalinnovation.javaspringbootpersonapi.dto.request.PasswordDTO;
import one.digitalinnovation.javaspringbootpersonapi.entity.Password;
import one.digitalinnovation.javaspringbootpersonapi.exception.RecursoNotFoundException;
import one.digitalinnovation.javaspringbootpersonapi.mapper.PasswordMapper;
import one.digitalinnovation.javaspringbootpersonapi.repository.PasswordRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PasswordServiceTest {

    @Mock
    PasswordRepository passwordRepository;

    @Mock
    PersonService personService;

    @Mock
    AttendantService attendantService;

    @Mock
    ProductItemService productItemService;

    private PasswordMapper passwordMapper = PasswordMapper.INSTANCE;

    @InjectMocks
    private PasswordService passwordService;

    @Test
    void whenPasswordInformedThenItShouldBeCreated() throws RecursoNotFoundException {
        // given
        PasswordDTO passwordDTO = PasswordDTOBuilder.builder().build().toPasswordDTO();
        passwordDTO.setPerson(PersonDTOBuilder.builder().birthDate("1988-03-23").build().toPersonDTO());
        Password expectedSavedPassword = passwordMapper.toModel(passwordDTO);

        // when
        Mockito.when(passwordRepository.save(expectedSavedPassword)).thenReturn(expectedSavedPassword);

        // then
        MessageResponseDTO expectedSuccessMessageResponseDTO = MessageResponseDTO.builder()
                .message("Created password with id " + expectedSavedPassword.getId())
                .build();

        MessageResponseDTO successMessageResponse = passwordService.createPassword(passwordDTO);

        Assertions.assertEquals(expectedSuccessMessageResponseDTO, successMessageResponse);
    }
}
