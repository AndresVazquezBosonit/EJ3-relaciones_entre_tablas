package CRUDvalidacionDTOSmodelMapper.insfrastructure.controller.dtos.inputs;

import CRUDvalidacionDTOSmodelMapper.domain.person.Person;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class StudentInputDTO {

    private String id_student;

    private String id_person;

    private int hours_per_week;

    private String comments;

    private String id_professor;

    private String branch;


}
