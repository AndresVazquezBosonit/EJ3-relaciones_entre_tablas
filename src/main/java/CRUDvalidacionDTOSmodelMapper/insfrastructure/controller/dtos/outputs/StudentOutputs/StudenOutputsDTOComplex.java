package CRUDvalidacionDTOSmodelMapper.insfrastructure.controller.dtos.outputs.StudentOutputs;

import CRUDvalidacionDTOSmodelMapper.domain.person.Person;
import CRUDvalidacionDTOSmodelMapper.domain.student.Student;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
public class StudenOutputsDTOComplex {

    private String id_student;
    private String id_person;
    private String username;
    private String name;
    private String surname;
    private String company_email;
    private String personal_email;
    private String city;
    private Date created_date;
    private String image_url;
    private int hours_per_week;
    private String comments;
    private String branch;

    public StudenOutputsDTOComplex(Student student) {

        this.id_student = student.getId_student();
        this.id_person = student.getId_person().getId_person();
        this.username = student.getId_person().getUsername();
        this.name = student.getId_person().getName();
        this.surname = student.getId_person().getSurname();
        this.company_email = student.getId_person().getCompany_email();
        this.personal_email = student.getId_person().getPersonal_email();
        this.city = student.getId_person().getCity();
        this.created_date = student.getId_person().getCreated_date();
        this.image_url = student.getId_person().getImage_url();
        this.hours_per_week = student.getHours_per_week();
        this.comments = student.getComments();
        this.branch = student.getBranch();
    }
}
