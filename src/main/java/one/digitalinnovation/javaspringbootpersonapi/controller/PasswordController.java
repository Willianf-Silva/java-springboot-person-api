package one.digitalinnovation.javaspringbootpersonapi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import one.digitalinnovation.javaspringbootpersonapi.dto.MessageResponseDTO;
import one.digitalinnovation.javaspringbootpersonapi.dto.request.PasswordDTO;
import one.digitalinnovation.javaspringbootpersonapi.exception.RecursoNotFoundException;
import one.digitalinnovation.javaspringbootpersonapi.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "API REST Senha de atendimento")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/password")
public class PasswordController {
    PasswordService passwordService;

    @Autowired
    public PasswordController(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @ApiOperation(value = "Incluir senha de atendimento.",
            notes = "Inclui uma nova senha de atendimento no banco de dados.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPassword(@RequestBody @Valid PasswordDTO passwordDTO){
        return passwordService.createPassword(passwordDTO);
    }

    @ApiOperation(value = "Retorna lista de senhas de atendimento.",
            notes = "Retorna uma lista com todas as senhas de atendimento cadastradas no banco de dados.")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PasswordDTO> listAll(){
        return passwordService.listAll();
    }

    @ApiOperation(value = "Retorna única senha de atendimento",
            notes = "Retorna uma única senha de atendimento de acordo com o id fornecido.")
    @GetMapping("/{id}")
    public PasswordDTO findById(@PathVariable Long id) throws RecursoNotFoundException {
        return passwordService.findById(id);
    }

    @ApiOperation(value = "Retorna lista de senhas conforme status.",
            notes = "Retorna uma lista com todas as senhas de acordo com o status fornecido.")
    @GetMapping("/list/{status}")
    public List<PasswordDTO> findByStatusOrderById(@PathVariable String status){
        return passwordService.findByStatusOrderById(status);
    }

    @ApiOperation(value = "Atualiza dados da senha de atendimento.",
            notes = "Atualiza uma senha no banco de dados de acordo com o id fornecido.")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDTO updatePassword(@PathVariable Long id,@RequestBody @Valid PasswordDTO passwordDTO) throws RecursoNotFoundException {
        return passwordService.updatedPassword(id, passwordDTO);
    }

    @ApiOperation(value = "Deleta senha de atendimento.",
            notes = "Deleta do banco de dados a senha fornecida.")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePassword(@PathVariable Long id) throws RecursoNotFoundException {
        passwordService.deletePassword(id);
    }
}