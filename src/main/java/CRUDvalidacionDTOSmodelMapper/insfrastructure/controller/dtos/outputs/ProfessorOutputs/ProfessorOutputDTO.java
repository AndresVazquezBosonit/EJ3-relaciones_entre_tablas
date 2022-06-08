package CRUDvalidacionDTOSmodelMapper.insfrastructure.controller.dtos.outputs.ProfessorOutputs;

import CRUDvalidacionDTOSmodelMapper.domain.person.Person;
import lombok.Data;

@Data
public class ProfessorOutputDTO {

    private String id_professor;

    private Person id_person;

    private String comments;

    private String branch;
}
