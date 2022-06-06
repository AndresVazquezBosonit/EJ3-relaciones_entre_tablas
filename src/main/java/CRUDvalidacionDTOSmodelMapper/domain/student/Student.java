package CRUDvalidacionDTOSmodelMapper.domain.student;

import CRUDvalidacionDTOSmodelMapper.domain.person.Person;
import CRUDvalidacionDTOSmodelMapper.domain.person.PersonsSequenceIdGenerator;
import CRUDvalidacionDTOSmodelMapper.domain.professor.Professor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "students_sequence")
    @GenericGenerator(
            name = "students_sequence",
            strategy = "CRUDvalidacionDTOSmodelMapper.domain.person.PersonsSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = PersonsSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = PersonsSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "STU"),
                    @org.hibernate.annotations.Parameter(name = PersonsSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")
            }
    )
    @Column(name = "id_student", nullable = false)
    private String id_student;

    @ManyToOne
    @JoinColumn(name = "id_person")
    @NotBlank
    private Person id_person;

    @Column(name = "hours_per_week")
    private int hours_per_week;

    @Column(name = "comments")
    private String comments;

    @NotBlank
    @ManyToOne
    private Professor id_professor;

    @Column(name = "branch")
    String branch;

}
