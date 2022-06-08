package CRUDvalidacionDTOSmodelMapper.insfrastructure.controller.professorController;

import CRUDvalidacionDTOSmodelMapper.aplication.ProfessorService;
import CRUDvalidacionDTOSmodelMapper.insfrastructure.controller.dtos.inputs.ProfessorInputDTO;
import CRUDvalidacionDTOSmodelMapper.insfrastructure.controller.dtos.outputs.ProfessorOutputs.ProfessorOutputDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @PostMapping
    public ResponseEntity<ProfessorOutputDTO> newProfessor(@RequestBody ProfessorInputDTO professorInputDTO) throws ParseException {

        return professorService.addProfessor(professorInputDTO);
    }

    @GetMapping
    public ResponseEntity<List<ProfessorOutputDTO>> listProfessors() {

        return professorService.findALlProfessors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorOutputDTO> findProfessor(@PathVariable String id) {

        return professorService.findByIdProfessor(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProfessor(@PathVariable String id) {

        return professorService.deleteProfessor(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorOutputDTO> updateProfessor(@RequestBody ProfessorInputDTO professorInputDTO, @PathVariable String id) {

        return professorService.updateProfessor(professorInputDTO, id);
    }


}
