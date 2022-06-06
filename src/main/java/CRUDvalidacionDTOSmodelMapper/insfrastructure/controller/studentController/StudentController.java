package CRUDvalidacionDTOSmodelMapper.insfrastructure.controller.studentController;

import CRUDvalidacionDTOSmodelMapper.aplication.StudentService;
import CRUDvalidacionDTOSmodelMapper.insfrastructure.controller.dto.input.StudentInputDTO;
import CRUDvalidacionDTOSmodelMapper.insfrastructure.controller.dto.output.StudentOutputDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentOutputDTO> addStudent(@RequestBody StudentInputDTO studentInputDTO) {

        return studentService.addStudent(studentInputDTO);
    }
    @GetMapping
    public ResponseEntity<List<StudentOutputDTO>> getStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentOutputDTO> getStudentById(@PathVariable String id, @RequestParam(defaultValue = "simple")String outputType){

        return studentService.getStudentById(id, outputType);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable String id){
        return studentService.deleteStudent(id);
    }
}
