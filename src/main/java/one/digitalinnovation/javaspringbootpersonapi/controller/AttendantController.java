package one.digitalinnovation.javaspringbootpersonapi.controller;

import one.digitalinnovation.javaspringbootpersonapi.dto.MessageResponseDTO;
import one.digitalinnovation.javaspringbootpersonapi.dto.request.AttendantDTO;
import one.digitalinnovation.javaspringbootpersonapi.entity.Attendant;
import one.digitalinnovation.javaspringbootpersonapi.exception.RecursoNotFoundException;
import one.digitalinnovation.javaspringbootpersonapi.service.AttendantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.MessageDigest;
import java.util.List;

/*
O controller é utilizado para fazer a comunicação com o client (front).
 */

@RestController
@RequestMapping("/api/v1/attendant")
public class AttendantController {
    private AttendantService attendantService;

    @Autowired
    public AttendantController(AttendantService attendantService) {
        this.attendantService = attendantService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO crateAttendant(@RequestBody @Valid AttendantDTO attendantDTO){
        return attendantService.createAttendant(attendantDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AttendantDTO> listAll(){
        return attendantService.listAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AttendantDTO findById(@PathVariable Long id) throws RecursoNotFoundException {
        return attendantService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws RecursoNotFoundException {
        attendantService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid AttendantDTO attendantDTO) throws RecursoNotFoundException {
        return attendantService.updateById(id, attendantDTO);
    }

}
