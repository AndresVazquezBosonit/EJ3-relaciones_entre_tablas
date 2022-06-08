package CRUDvalidacionDTOSmodelMapper.domain.student;

import CRUDvalidacionDTOSmodelMapper.domain.person.Person;
import CRUDvalidacionDTOSmodelMapper.domain.person.PersonsSequenceIdGenerator;
import CRUDvalidacionDTOSmodelMapper.domain.professor.Professor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

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
                    @Parameter(name = PersonsSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = PersonsSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "STU"),
                    @Parameter(name = PersonsSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")
            }
    )
    @Column(name = "id_student", nullable = false)
    private String id_student;

    @OneToOne
    @JoinColumn(name = "id_person")
    private Person id_person;

   // @Column(name = "hours_per_week")
    private int hours_per_week;

    //@Column(name = "comments")
    private String comments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_professor")
    private Professor id_professor;

    //@Column(name = "branch")
    String branch;

}
