package CRUDvalidacionDTOSmodelMapper.insfrastructure.controller.dtos.outputs.PersonOutputs;

import CRUDvalidacionDTOSmodelMapper.domain.professor.Professor;
import lombok.Data;

import java.util.Date;
@Data
public class PersonOutputDTOProfessor {
    private String id_person;
    private String username;
    private String name;
    private String surname;
    private String company_email;
    private String personal_email;
    private String city;
    private Boolean active;
    private Date created_date;
    private String image_url;
    private Date termination_date;
    private Professor professor;
}
