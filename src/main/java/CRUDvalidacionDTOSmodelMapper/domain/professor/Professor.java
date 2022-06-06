package CRUDvalidacionDTOSmodelMapper.domain.professor;

import CRUDvalidacionDTOSmodelMapper.domain.person.Person;
import CRUDvalidacionDTOSmodelMapper.domain.person.PersonsSequenceIdGenerator;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@Table(name = "professors")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "professor_sequence")
    @GenericGenerator(
            name = "professor_sequence",
            strategy = "CRUDvalidacionDTOSmodelMapper.domain.person.PersonsSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = PersonsSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = PersonsSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "PROF"),
                    @org.hibernate.annotations.Parameter(name = PersonsSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%03d")
            }
    )
    @Column(name = "id_professor", nullable = false)
    private String id_professor;

    @ManyToOne
    @JoinColumn(name = "id_person_id_person")
    @NotBlank
    private Person id_person;

    private String comments;

    private String branch;


}
