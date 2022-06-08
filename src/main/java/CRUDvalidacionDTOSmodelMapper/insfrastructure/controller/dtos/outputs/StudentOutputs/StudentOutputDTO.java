package CRUDvalidacionDTOSmodelMapper.insfrastructure.controller.dtos.outputs.StudentOutputs;

import CRUDvalidacionDTOSmodelMapper.domain.person.Person;
import CRUDvalidacionDTOSmodelMapper.domain.professor.Professor;
import lombok.Data;

@Data
public class StudentOutputDTO {

    private String id_student;

    private Person id_person;

    private int hours_per_week;

    private String comments;

    private String branch;

}
