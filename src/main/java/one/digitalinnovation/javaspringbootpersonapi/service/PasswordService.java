package one.digitalinnovation.javaspringbootpersonapi.service;

import one.digitalinnovation.javaspringbootpersonapi.dto.MessageResponseDTO;
import one.digitalinnovation.javaspringbootpersonapi.dto.request.PasswordDTO;
import one.digitalinnovation.javaspringbootpersonapi.entity.Password;
import one.digitalinnovation.javaspringbootpersonapi.exception.RecursoNotFoundException;
import one.digitalinnovation.javaspringbootpersonapi.mapper.PasswordMapper;
import one.digitalinnovation.javaspringbootpersonapi.repository.PasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public PasswordDTO findById(Long id) throws RecursoNotFoundException {
        verifyIfExists(id);
        Optional<Password> optionalPassword = passwordRepository.findById(id);
        return passwordMapper.toDTO(optionalPassword.get());
    }

    public List<PasswordDTO> findByStatus(String status) {
        List<Password> allPasswordByStatus = passwordRepository.findByStatus(status);

        return allPasswordByStatus.stream()
                .map(passwordMapper::toDTO)
                .collect(Collectors.toList());
    }

    public MessageResponseDTO updatedPassword(Long id, PasswordDTO passwordDTO) throws RecursoNotFoundException {
        Password updatedPaswword = null;

        if (passwordDTO.getId() == id){
            verifyIfExists(id);
            updatedPaswword = passwordRepository.save(passwordMapper.toModel(passwordDTO));
        }

        return messageResponse("Updated password with id ", updatedPaswword.getId());
    }

    public void deletePassword(Long id) throws RecursoNotFoundException {
        verifyIfExists(id);
        passwordRepository.deleteById(id);
    }
}
