package one.digitalinnovation.javaspringbootpersonapi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import one.digitalinnovation.javaspringbootpersonapi.dto.MessageResponseDTO;
import one.digitalinnovation.javaspringbootpersonapi.dto.request.PersonDTO;
import one.digitalinnovation.javaspringbootpersonapi.exception.RecursoNotFoundException;
import one.digitalinnovation.javaspringbootpersonapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "API REST Cliente")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/peoples")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {

    private PersonService personService;

    @ApiOperation(value = "Incluir cliente.",
            notes = "Inclui um novo cliente no banco de dados.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO){
        return personService.createPerson(personDTO);
    }

    @ApiOperation(value = "Retorna lista de clientes.",
            notes = "Retorna uma lista com todos os clientes cadastrados no banco de dados.")
    @GetMapping
    public List<PersonDTO> listAll(){
        return personService.listAll();
    }

    @ApiOperation(value = "Retorna único cliente.",
            notes = "Retorna um cliente único de acordo com o id fornecido.")
    @GetMapping("/{id}")
    public PersonDTO findById(@PathVariable Long id) throws RecursoNotFoundException {
        return  personService.findById(id);
    }

    @ApiOperation(value = "Atualiza dados do cliente.",
            notes = "Atualiza um cliente no banco de dados de acordo com o id fornecido.")
    @PutMapping("/{id}")
    public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid PersonDTO personDTO) throws RecursoNotFoundException {
        return personService.upddateById(id, personDTO);
    }

    @ApiOperation(value = "Deleta cliente.",
            notes = "Deleta do banco de dados o cliente com o id fornecido.")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws RecursoNotFoundException {
        personService.delete(id);
    }
}
