package CRUDvalidacionDTOSmodelMapper.domain.professor;

import CRUDvalidacionDTOSmodelMapper.domain.person.Person;
import CRUDvalidacionDTOSmodelMapper.domain.person.PersonsSequenceIdGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "professors")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "professor_sequence")
    @GenericGenerator(
            name = "professor_sequence",
            strategy = "CRUDvalidacionDTOSmodelMapper.domain.person.PersonsSequenceIdGenerator",
            parameters = {
                    @Parameter(name = PersonsSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = PersonsSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "PROF"),
                    @Parameter(name = PersonsSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%03d")
            }
    )
    @Column(name = "id_professor", nullable = false)
    private String id_professor;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_person")
    private Person id_person;

    private String comments;

    private String branch;

}
