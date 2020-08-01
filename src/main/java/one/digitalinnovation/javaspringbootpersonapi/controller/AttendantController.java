package one.digitalinnovation.javaspringbootpersonapi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import one.digitalinnovation.javaspringbootpersonapi.dto.MessageResponseDTO;
import one.digitalinnovation.javaspringbootpersonapi.dto.request.AttendantDTO;
import one.digitalinnovation.javaspringbootpersonapi.exception.RecursoNotFoundException;
import one.digitalinnovation.javaspringbootpersonapi.service.AttendantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "API REST Cliente")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/attendants")
public class AttendantController {
    private AttendantService attendantService;

    @Autowired
    public AttendantController(AttendantService attendantService) {
        this.attendantService = attendantService;
    }

    @ApiOperation(value = "Incluir atendente.",
            notes = "Inclui um novo atendente no banco de dados")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO crateAttendant(@RequestBody @Valid AttendantDTO attendantDTO){
        return attendantService.createAttendant(attendantDTO);
    }

    @ApiOperation(value = "Retorna lista de atendentes.",
            notes = "Retorna uma lista com todos os atendentes cadastrados no banco de dados.")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AttendantDTO> listAll(){
        return attendantService.listAll();
    }

    @ApiOperation(value = "Retorna único atendente.",
            notes = "Retorna um atendente único de acordo com o id fornecido.")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AttendantDTO findById(@PathVariable Long id) throws RecursoNotFoundException {
        return attendantService.findById(id);
    }

    @ApiOperation(value = "Deleta atendente.",
            notes = "Deleta do banco de dados o atendente com o id fornecido.")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws RecursoNotFoundException {
        attendantService.delete(id);
    }

    @ApiOperation(value = "Atualiza dados do atendente.",
            notes = "Atualiza um atendente no banco de dados de acordo com o id fornecido.")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid AttendantDTO attendantDTO) throws RecursoNotFoundException {
        return attendantService.updateById(id, attendantDTO);
    }

}
