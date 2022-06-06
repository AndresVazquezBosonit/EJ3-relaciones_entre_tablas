package CRUDvalidacionDTOSmodelMapper.insfrastructure.controller.dto.input;

import CRUDvalidacionDTOSmodelMapper.domain.person.Person;
import lombok.Data;



@Data
public class ProfessorInputDTO {

    private String id_professor;

    private String id_person;

    private String comments;

    private String branch;
}
