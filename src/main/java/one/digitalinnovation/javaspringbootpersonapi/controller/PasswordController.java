package one.digitalinnovation.javaspringbootpersonapi.controller;

import one.digitalinnovation.javaspringbootpersonapi.dto.MessageResponseDTO;
import one.digitalinnovation.javaspringbootpersonapi.dto.request.PasswordDTO;
import one.digitalinnovation.javaspringbootpersonapi.exception.RecursoNotFoundException;
import one.digitalinnovation.javaspringbootpersonapi.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/password")
public class PasswordController {
    PasswordService passwordService;

    @Autowired
    public PasswordController(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPassword(@RequestBody @Valid PasswordDTO passwordDTO){
        return passwordService.createPassword(passwordDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PasswordDTO> listAll(){
        return passwordService.listAll();
    }

    @GetMapping("/{id}")
    public PasswordDTO findById(@PathVariable Long id) throws RecursoNotFoundException {
        return passwordService.findById(id);
    }

    @GetMapping("/list/{status}")
    public List<PasswordDTO> findByStatusOrderById(@PathVariable String status){
        return passwordService.findByStatusOrderById(status);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDTO updatePassword(@PathVariable Long id,@RequestBody @Valid PasswordDTO passwordDTO) throws RecursoNotFoundException {
        return passwordService.updatedPassword(id, passwordDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePassword(@PathVariable Long id) throws RecursoNotFoundException {
        passwordService.deletePassword(id);
    }
}
