package CRUDvalidacionDTOSmodelMapper.aplication;

import CRUDvalidacionDTOSmodelMapper.domain.person.Person;
import CRUDvalidacionDTOSmodelMapper.domain.professor.Professor;
import CRUDvalidacionDTOSmodelMapper.insfrastructure.controller.dtos.inputs.ProfessorInputDTO;
import CRUDvalidacionDTOSmodelMapper.insfrastructure.controller.dtos.outputs.ProfessorOutputs.ProfessorOutputDTO;
import CRUDvalidacionDTOSmodelMapper.insfrastructure.exceptions.NotFoundException;
import CRUDvalidacionDTOSmodelMapper.insfrastructure.repository.PersonRepository;
import CRUDvalidacionDTOSmodelMapper.insfrastructure.repository.ProfessorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    ModelMapper modelMapper;

    public ResponseEntity<ProfessorOutputDTO> addProfessor(ProfessorInputDTO professorInputDTO) {

        Optional<Person> personEntity = personRepository.findById(professorInputDTO.getId_person());

        if (personEntity.isPresent()) {

            Professor professorEntity = modelMapper.map(professorInputDTO, Professor.class);
            professorRepository.save(professorEntity);

            //personEntity.get().setId_professor(professorEntity);
            //personRepository.saveAndFlush(personEntity.get());

            ProfessorOutputDTO professorOutputDTO = modelMapper.map(professorInputDTO, ProfessorOutputDTO.class);
            professorOutputDTO.setId_professor(professorEntity.getId_professor());

            //professorOutputDTO.setId_person(personEntity.get());

            return new ResponseEntity<>(professorOutputDTO, HttpStatus.CREATED);

        } else
            throw new NotFoundException("the person with id: " + professorInputDTO.getId_person() + " does not exist");

    }

    public ResponseEntity<List<ProfessorOutputDTO>> findALlProfessors() {

        List<ProfessorOutputDTO> professorOutputDTOList = new ArrayList<>();

        List<Professor> professorsList = professorRepository.findAll();

        professorsList.forEach(professor -> {

            professorOutputDTOList.add(modelMapper.map(professor, ProfessorOutputDTO.class));

        });
        return new ResponseEntity<>(professorOutputDTOList, HttpStatus.OK);
    }

    public ResponseEntity<ProfessorOutputDTO> findByIdProfessor(String id) {

        Optional<Professor> professorEntity = professorRepository.findById(id);

        if (professorEntity.isPresent()) {

            ProfessorOutputDTO professorOutputDTO = modelMapper.map(professorEntity, ProfessorOutputDTO.class);

            return new ResponseEntity<>(professorOutputDTO, HttpStatus.OK);

        } else {

            throw new NotFoundException("The professor with id: " + id + " does not exist");
        }
    }

    public ResponseEntity<String> deleteProfessor(String id) {

        Optional<Professor> professorToDelete = professorRepository.findById(id);

        if (professorToDelete.isPresent()) {

            professorRepository.deleteById(id);

            return new ResponseEntity<>("Has been deleted the professor with id: " +
                    professorToDelete.get().getId_professor(),
                    HttpStatus.OK);
        } else {

            throw new NotFoundException("the professor with id: " + id + " does not exist");
        }

    }

    public ResponseEntity<ProfessorOutputDTO> updateProfessor(ProfessorInputDTO professorInputDTO, String id) {

        Optional<Professor> professorEntity = professorRepository.findById(id);

        if (professorEntity.isPresent()) {
            professorInputDTO.setId_professor(id);
            professorInputDTO.setId_person(Optional.ofNullable(professorInputDTO.getId_person()).orElse(professorEntity.get().getId_person().getId_person()));
            professorInputDTO.setComments(Optional.ofNullable(professorInputDTO.getComments()).orElse(professorEntity.get().getComments()));
            professorInputDTO.setBranch(Optional.ofNullable(professorInputDTO.getBranch()).orElse(professorEntity.get().getBranch()));

            professorRepository.saveAndFlush(modelMapper.map(professorInputDTO, Professor.class));

            Optional<Person> personEntity = personRepository.findById(professorEntity.get().getId_person().getId_person());


            ProfessorOutputDTO professorOutputDTO = modelMapper.map(professorInputDTO, ProfessorOutputDTO.class);
            professorOutputDTO.setId_person(personEntity.get());

            return new ResponseEntity<>(professorOutputDTO, HttpStatus.ACCEPTED);

        } else {

            throw new NotFoundException("the professor with id: " + id + " that you are trying to update does not exist");
        }
    }

}
