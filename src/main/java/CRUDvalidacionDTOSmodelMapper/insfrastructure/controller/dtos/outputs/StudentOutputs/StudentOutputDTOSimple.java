package CRUDvalidacionDTOSmodelMapper.insfrastructure.controller.dtos.outputs.StudentOutputs;

import CRUDvalidacionDTOSmodelMapper.domain.student.Student;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class StudentOutputDTOSimple {

    private String id_student;

    private String id_person;

    private int hours_per_week;

    private String comments;

    //private Professor id_professor;

    private String branch;

    public StudentOutputDTOSimple(Student studentEntity) {

        this.id_student = studentEntity.getId_student();
        this.id_person = studentEntity.getId_person().getId_person();
        this.hours_per_week = studentEntity.getHours_per_week();
        this.comments = studentEntity.getComments();
        this.branch = studentEntity.getBranch();

    }

}


