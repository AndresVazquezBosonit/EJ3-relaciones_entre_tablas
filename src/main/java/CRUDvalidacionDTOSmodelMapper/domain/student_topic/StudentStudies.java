package CRUDvalidacionDTOSmodelMapper.domain.student_topic;

import CRUDvalidacionDTOSmodelMapper.domain.person.PersonsSequenceIdGenerator;
import CRUDvalidacionDTOSmodelMapper.domain.professor.Professor;
import CRUDvalidacionDTOSmodelMapper.domain.student.Student;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "StudentStudies")
public class StudentStudies {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "studies_sequence")
    @GenericGenerator(
            name = "studies_sequence",
            strategy = "CRUDvalidacionDTOSmodelMapper.domain.person.PersonsSequenceIdGenerator",
            parameters = {
                    @Parameter(name = PersonsSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = PersonsSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "TOPIC"),
                    @Parameter(name = PersonsSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%03d")
            }
    )
    private String id_study;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_professor")
    private Professor id_professor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_student")
    private Student student;

    @Column(name = "subject")
    String subject;

    @Column(name = "comments")
    String comment;

    @Column(name = "initial_date")
    Date initial_date;

    @Column(name = "finish_date")
    Date finish_date;

}
