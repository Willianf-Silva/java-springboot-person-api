package one.digitalinnovation.javaspringbootpersonapi.service;

import one.digitalinnovation.javaspringbootpersonapi.dto.MessageResponseDTO;
import one.digitalinnovation.javaspringbootpersonapi.dto.request.AttendantDTO;
import one.digitalinnovation.javaspringbootpersonapi.entity.Attendant;
import one.digitalinnovation.javaspringbootpersonapi.exception.RecursoNotFoundException;
import one.digitalinnovation.javaspringbootpersonapi.mapper.AttendantMapper;
import one.digitalinnovation.javaspringbootpersonapi.repository.AttendantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AttendantService {
    private final AttendantMapper attendantMapper = AttendantMapper.INSTANCE;
    private AttendantRepository attendantRepository;

    @Autowired
    public AttendantService(AttendantRepository attendantRepository) {
        this.attendantRepository = attendantRepository;
    }

    public MessageResponseDTO createAttendant(AttendantDTO attendantDTO) {
        // Salvando em uma variável o objeto atendente que foi convertido de DTO para ENTITY
        Attendant attendantToSave = attendantMapper.toModel(attendantDTO);

        // Salvando em uma variável o objeto que foi salvo no banco de dados
        Attendant savedAttendant = attendantRepository.save(attendantToSave);

        return messageResponse("Created attendant with id: ", savedAttendant.getId());
    }

    public List<AttendantDTO> listAll() {
        List<Attendant> allAttendant = attendantRepository.findAll();
        return allAttendant.stream()
                .map(attendantMapper::toDTO)
                .collect(Collectors.toList());
    }

    public AttendantDTO findById(Long id) throws RecursoNotFoundException {
        Optional<Attendant> optionalAttendant = attendantRepository.findById(id);

        if (optionalAttendant.isEmpty()){
            throw new RecursoNotFoundException("Attendant not found with ID: ", id);
        }
        return attendantMapper.toDTO(optionalAttendant.get());
    }


    public void delete(Long id) throws RecursoNotFoundException {
        verifyIfExistis(id);
        attendantRepository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id, AttendantDTO attendantDTO) throws RecursoNotFoundException {
        verifyIfExistis(id);
        Attendant updateAttendant = attendantRepository.save(attendantMapper.toModel(attendantDTO));
        return messageResponse("Update attendant with id ", updateAttendant.getId());
    }

    private Attendant verifyIfExistis(Long id) throws RecursoNotFoundException {
        return attendantRepository.findById(id).orElseThrow(()-> new RecursoNotFoundException("Attendant not found with ID: ", id));
    }


    private MessageResponseDTO messageResponse(String message, Long id) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }
}
