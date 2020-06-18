package one.digitalinnovation.javaspringbootpersonapi.controller;

import one.digitalinnovation.javaspringbootpersonapi.dto.MessageResponseDTO;
import one.digitalinnovation.javaspringbootpersonapi.dto.request.PasswordDTO;
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
}