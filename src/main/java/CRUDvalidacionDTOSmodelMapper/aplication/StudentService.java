package CRUDvalidacionDTOSmodelMapper.aplication;

import CRUDvalidacionDTOSmodelMapper.domain.person.Person;
import CRUDvalidacionDTOSmodelMapper.domain.student.Student;
import CRUDvalidacionDTOSmodelMapper.insfrastructure.controller.dtos.inputs.StudentInputDTO;
import CRUDvalidacionDTOSmodelMapper.insfrastructure.controller.dtos.outputs.StudentOutputs.StudenOutputsDTOComplex;
import CRUDvalidacionDTOSmodelMapper.insfrastructure.controller.dtos.outputs.StudentOutputs.StudentOutputDTO;
import CRUDvalidacionDTOSmodelMapper.insfrastructure.controller.dtos.outputs.StudentOutputs.StudentOutputDTOSimple;
import CRUDvalidacionDTOSmodelMapper.insfrastructure.exceptions.NotFoundException;
import CRUDvalidacionDTOSmodelMapper.insfrastructure.repository.PersonRepository;
import CRUDvalidacionDTOSmodelMapper.insfrastructure.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseEntity<StudentOutputDTOSimple> addStudent(StudentInputDTO studentInputDTO) {

        Optional<Person> personInDB = personRepository.findById(studentInputDTO.getId_person());

        if (personInDB.isPresent()) {

            Student studentEntity = modelMapper.map(studentInputDTO, Student.class);

            studentEntity.setId_person(personInDB.get());

            studentRepository.saveAndFlush(studentEntity);

            studentInputDTO.setId_student(studentEntity.getId_student());

            StudentOutputDTOSimple studentOutputDTOSimple = modelMapper.map(studentInputDTO, StudentOutputDTOSimple.class);

            return new ResponseEntity<>(studentOutputDTOSimple, HttpStatus.CREATED);

        } else {

            throw new NotFoundException("the person with id: " + studentInputDTO.getId_person() + " does not exist");
        }
    }

    public ResponseEntity<List<StudentOutputDTO>> getAllStudents() {

        List<Student> studentsLis = studentRepository.findAll();

        List<StudentOutputDTO> studentsOutputDTOList = new ArrayList<>();


        studentsLis.forEach(
                students -> {
                    StudentOutputDTO studentOutputDTO = new StudentOutputDTO();
                    studentOutputDTO = modelMapper.map(students, StudentOutputDTO.class);
                    studentsOutputDTOList.add(studentOutputDTO);
                }
        );

        return new ResponseEntity<>(studentsOutputDTOList, HttpStatus.OK);
    }

    public ResponseEntity<Object> getStudentById(String id, String outputType) {

        Optional<Student> studentEntity = studentRepository.findById(id);

        if (studentEntity.isPresent()) {

            if (outputType.equalsIgnoreCase("full")) {

                StudenOutputsDTOComplex studenOutputsDTOComplex = new StudenOutputsDTOComplex(studentEntity.get());

                return new ResponseEntity<>(studenOutputsDTOComplex, HttpStatus.OK);

            } else if (outputType.equalsIgnoreCase("simple")){

                ///// ----------------------- Also works with Constructor ---------------------- /////
                //StudentOutputDTOSimple studentOutputDTOSimple = new StudentOutputDTOSimple(studentEntity.get());

                StudentOutputDTOSimple studentOutputDTOSimple = modelMapper.map(studentEntity, StudentOutputDTOSimple.class);

                return new ResponseEntity<>(studentOutputDTOSimple, HttpStatus.OK);

            } else {
                throw new NotFoundException("Please remove the outputType from the path, leave only the student id or put 'simple' or 'full' in the path.");
            }

        } else {

            throw new NotFoundException("The student with id: " + id + "does not exist");
        }

    }

    /*
    public ResponseEntity<List<StudentOutputDTO>> studentByName(String name) {

        List<Student> studentsEntityList = studentRepository.findByName(name);

        List<StudentOutputDTO> studentOutputDTOList = new ArrayList<>();

        studentsEntityList.forEach(student -> {
            StudentOutputDTO studentOutputDTO = modelMapper.map(student, StudentOutputDTO.class);
            studentOutputDTOList.add(studentOutputDTO);
        });

        return new ResponseEntity<>(studentOutputDTOList, HttpStatus.OK);
    }
    */

    public ResponseEntity<String> deleteStudent(String id) {
        Optional<Student> studentToDelete = studentRepository.findById(id);
        if (studentToDelete.isPresent()) {

            studentRepository.deleteById(id);

            return new ResponseEntity<>("Has been deleted student with id: "
                    + studentToDelete.get().getId_student(), HttpStatus.OK);
        } else {

            throw new NotFoundException("The student with id: " + id + "does not exist");
        }
    }

}
