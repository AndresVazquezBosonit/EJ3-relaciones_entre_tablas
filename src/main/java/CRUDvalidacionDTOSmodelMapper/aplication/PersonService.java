package CRUDvalidacionDTOSmodelMapper.aplication;

import CRUDvalidacionDTOSmodelMapper.domain.person.Person;
import CRUDvalidacionDTOSmodelMapper.insfrastructure.controller.dto.input.PersonInputDTO;
import CRUDvalidacionDTOSmodelMapper.insfrastructure.controller.dto.output.PersonOutputDTO;
import CRUDvalidacionDTOSmodelMapper.insfrastructure.exceptions.NotFoundException;
import CRUDvalidacionDTOSmodelMapper.insfrastructure.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ModelMapper modelMapper;


    ///// ----------------------- Add new Person -----------------------/////
    public ResponseEntity<PersonOutputDTO> addPerson(PersonInputDTO personInputDTO) throws ParseException {

        LocalDate creationDate = LocalDate.now();

        PersonOutputDTO personOutputDTO = new PersonOutputDTO();

        Person personEntity =
                personRepository.save(modelMapper.map(personInputDTO, Person.class));

        personOutputDTO = modelMapper.map(personInputDTO, PersonOutputDTO.class);
        personOutputDTO.setId_person(personEntity.getId_person());
        return new ResponseEntity<>(personOutputDTO, HttpStatus.CREATED);
    }

    ///// ----------------------------- Get All person ---------------------/////
    public ResponseEntity<List<PersonOutputDTO>> listPerson() {

        List<PersonOutputDTO> personList = new ArrayList<>();

        personRepository.findAll()
                .forEach(
                        person -> {
                            PersonOutputDTO personOutputDTO = new PersonOutputDTO();
                            personOutputDTO = modelMapper.map(person, PersonOutputDTO.class);
                            personList.add(personOutputDTO);
                        });

        return new ResponseEntity<>(personList, HttpStatus.OK);
    }

    ///// ---------------------------- Find Person By Id ------------------------/////
    public ResponseEntity<PersonOutputDTO> personById(String id) throws Exception {

        Optional<Person> personInBD = personRepository.findById(id);

        HttpHeaders httpHeaders = new HttpHeaders();

        if (personInBD.isPresent()) {

            PersonOutputDTO personOutputDTO = new PersonOutputDTO();
            personOutputDTO = modelMapper.map(personInBD, PersonOutputDTO.class);

            httpHeaders.add("Message", "User exist");

            return new ResponseEntity<>(personOutputDTO, httpHeaders, HttpStatus.OK);

        } else {

            throw new NotFoundException("the person does not exist");
        }
    }

    ///// ------------------------------- Find By Name ----------------------------- /////
    public ResponseEntity<List<PersonOutputDTO>> personByName(String name) {

        List<Person> personsInBD = personRepository.findByName(name);

        List<PersonOutputDTO> lisPersonOutputDTOS = new ArrayList<>();

        personsInBD
                .forEach(person -> {
                    PersonOutputDTO personOutputDTO = modelMapper.map(person, PersonOutputDTO.class);
                    lisPersonOutputDTOS.add(personOutputDTO);
                });

        return new ResponseEntity<>(lisPersonOutputDTOS, HttpStatus.OK);
    }

    ///// ------------------------------- Delete Person --------------------------- /////
    public ResponseEntity<String> deletePersona(String id) throws Exception {

        Optional<Person> personToDelete = personRepository.findById(id);

        if (personToDelete.isPresent()) {

            personRepository.deleteById(id);

            return new ResponseEntity<>("Has been deleted: "
                    + personToDelete.get().getName()
                    + " from: "
                    + personToDelete.get().getCity()
                    + " with id: "
                    + personToDelete.get().getId_person(), HttpStatus.OK);
        } else {

            throw new NotFoundException("the person does not exist");
        }
    }

    ///// -------------------------------- Update Person ----------------------------- /////
    public ResponseEntity<PersonOutputDTO> updatePerson(PersonInputDTO personInputDTO, String id) throws Exception {

        Optional<Person> personEntity = personRepository.findById(id);

        if (personEntity.isPresent()) {
            personInputDTO.setId_person(id);
            personInputDTO.setUsername(
                    Optional.ofNullable(personInputDTO.getUsername())
                            .orElse(personEntity.get().getUsername()));
            personInputDTO.setPassword(
                    Optional.ofNullable(personInputDTO.getPassword())
                            .orElse(personEntity.get().getPassword()));
            personInputDTO.setName(
                    Optional.ofNullable(personInputDTO.getName()).orElse(personEntity.get().getName()));
            personInputDTO.setSurname(
                    Optional.ofNullable(personInputDTO.getSurname()).orElse(personEntity.get().getSurname()));
            personInputDTO.setCompany_email(
                    Optional.ofNullable(personInputDTO.getCompany_email())
                            .orElse(personEntity.get().getCompany_email()));
            personInputDTO.setPersonal_email(
                    Optional.ofNullable(personInputDTO.getPersonal_email())
                            .orElse(personEntity.get().getPersonal_email()));
            personInputDTO.setCity(
                    Optional.ofNullable(personInputDTO.getCity()).orElse(personEntity.get().getCity()));
            personInputDTO.setImage_url(
                    Optional.ofNullable(personInputDTO.getImage_url())
                            .orElse(personEntity.get().getImage_url()));
            personInputDTO.setActive(personEntity.get().getActive());
            personInputDTO.setCreated_date(personEntity.get().getCreated_date());
            personRepository.saveAndFlush(modelMapper.map(personInputDTO, Person.class));
            PersonOutputDTO personOutputDTO = modelMapper.map(personInputDTO, PersonOutputDTO.class);

            return new ResponseEntity<>(personOutputDTO, HttpStatus.ACCEPTED);

        } else {

            throw new NotFoundException("the person does not exist");

        }
    }
}
