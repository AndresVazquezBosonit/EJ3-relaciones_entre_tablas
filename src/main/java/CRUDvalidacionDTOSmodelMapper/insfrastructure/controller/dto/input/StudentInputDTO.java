package CRUDvalidacionDTOSmodelMapper.insfrastructure.controller.dto.input;

import CRUDvalidacionDTOSmodelMapper.domain.professor.Professor;
import lombok.Data;

import java.util.List;

@Data
public class StudentInputDTO {

    private String id_student;

    private String id_person;

    private int hours_per_week;

    private String comments;

    private String id_professor;

    private String branch;


}
