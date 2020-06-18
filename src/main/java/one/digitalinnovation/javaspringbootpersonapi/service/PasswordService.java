package one.digitalinnovation.javaspringbootpersonapi.service;

import one.digitalinnovation.javaspringbootpersonapi.dto.MessageResponseDTO;
import one.digitalinnovation.javaspringbootpersonapi.dto.request.PasswordDTO;
import one.digitalinnovation.javaspringbootpersonapi.dto.request.ProductItemDTO;
import one.digitalinnovation.javaspringbootpersonapi.entity.Password;
import one.digitalinnovation.javaspringbootpersonapi.exception.RecursoNotFoundException;
import one.digitalinnovation.javaspringbootpersonapi.mapper.PasswordMapper;
import one.digitalinnovation.javaspringbootpersonapi.repository.PasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

@Service
public class PasswordService {
    PasswordMapper passwordMapper = PasswordMapper.INSTANCE;
    PasswordRepository passwordRepository;

    @Autowired
    public PasswordService(PasswordRepository passwordRepository) {
        this.passwordRepository = passwordRepository;
    }

    private MessageResponseDTO messageResponse(String message, Long id) {
        return MessageResponseDTO.builder()
                .message(message + id)
                .build();
    }

    private Password verifyIfExists(Long id) throws RecursoNotFoundException {
        return passwordRepository.findById(id).orElseThrow(()-> new RecursoNotFoundException("Password not found with ID ", id));
    }

    public MessageResponseDTO createPassword(PasswordDTO passwordDTO) {
        double cost = passwordDTO.getProductItems()
                .stream()
                .mapToDouble(item -> item.getTotalValue())
                .sum();

        passwordDTO.setCost(cost);
        Password passwordEntity = passwordMapper.toModel(passwordDTO);
        Password savedPassword = passwordRepository.save(passwordEntity);
        return messageResponse("Created password with id ", savedPassword.getId());
    }

    public List<PasswordDTO> listAll() {
        List<Password> allPassword = passwordRepository.findAll();

        return allPassword.stream()
                .map(passwordMapper::toDTO)
                .collect(Collectors.toList());
    }
}
