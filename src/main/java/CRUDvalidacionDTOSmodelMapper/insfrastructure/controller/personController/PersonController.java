package CRUDvalidacionDTOSmodelMapper.insfrastructure.controller.personController;

import CRUDvalidacionDTOSmodelMapper.insfrastructure.controller.dtos.inputs.PersonInputDTO;
import CRUDvalidacionDTOSmodelMapper.insfrastructure.controller.dtos.outputs.PersonOutputs.PersonOutputDTO;
import CRUDvalidacionDTOSmodelMapper.aplication.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping
    public ResponseEntity<PersonOutputDTO> newPerson(@Valid @RequestBody PersonInputDTO personInputDTO) throws ParseException {

        return personService.addPerson(personInputDTO);
    }

    @GetMapping
    public ResponseEntity<List<PersonOutputDTO>> personList() {
        return personService.listPerson();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> personById(@PathVariable String id) throws Exception {
        return personService.personById(id);
    }

    @GetMapping("/name")
    public ResponseEntity<List<PersonOutputDTO>> personListByName(@RequestParam String name) throws Exception {
        return personService.personByName(name);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable String id) throws Exception {
        return personService.deletePersona(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonOutputDTO> updatePerson(@RequestBody PersonInputDTO person, @PathVariable String id)
            throws Exception {
        return personService.updatePerson(person, id);
    }

}
