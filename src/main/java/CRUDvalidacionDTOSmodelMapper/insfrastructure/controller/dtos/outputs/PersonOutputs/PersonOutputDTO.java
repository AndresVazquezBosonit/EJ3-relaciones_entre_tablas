package CRUDvalidacionDTOSmodelMapper.insfrastructure.controller.dtos.outputs.PersonOutputs;

import CRUDvalidacionDTOSmodelMapper.domain.professor.Professor;
import CRUDvalidacionDTOSmodelMapper.domain.student.Student;
import lombok.Data;

import java.util.Date;

/////------------ Person to show except password ------------/////
@Data
public class PersonOutputDTO {
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
  private Student student;
}
