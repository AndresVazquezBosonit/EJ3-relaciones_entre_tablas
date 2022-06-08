package CRUDvalidacionDTOSmodelMapper.insfrastructure.controller.dtos.inputs;

import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class ProfessorInputDTO {

    private String id_professor;
    @NotBlank
    private String id_person;

    private String comments;

    private String branch;
}
