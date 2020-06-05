package one.digitalinnovation.javaspringbootpersonapi.service;

import one.digitalinnovation.javaspringbootpersonapi.dto.MessageResponseDTO;
import one.digitalinnovation.javaspringbootpersonapi.dto.request.AttendantDTO;
import one.digitalinnovation.javaspringbootpersonapi.entity.Attendant;
import one.digitalinnovation.javaspringbootpersonapi.mapper.AttendantMapper;
import one.digitalinnovation.javaspringbootpersonapi.mapper.PersonMapper;
import one.digitalinnovation.javaspringbootpersonapi.repository.AttendantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        return createdMessageResponse(savedAttendant.getId(), "Created attendant with id: ");
    }

    private MessageResponseDTO createdMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }
}
