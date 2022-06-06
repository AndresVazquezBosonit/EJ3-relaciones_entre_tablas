package CRUDvalidacionDTOSmodelMapper.domain.person;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Parameter;

import java.util.Date;

@Entity
@Data
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "persons_sequence")
    @GenericGenerator(
            name = "persons_sequence",
            strategy = "CRUDvalidacionDTOSmodelMapper.domain.person.PersonsSequenceIdGenerator",
            parameters = {
                    @Parameter(name = PersonsSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = PersonsSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "PER"),
                    @Parameter(name = PersonsSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")
            }
    )
    @Column(name = "id_person", nullable = false)
    private String id_person;

    @Column(name = "username")
    @NotBlank(message = "The username cannot be null or empty")
    @Size(min = 6, max = 10, message = "The username must be between 6 and 10 characters long")
    private String username;

    @NotBlank(message = "The password cannot be null or empty")
    @Column(name = "password")
    private String password;

    @NotBlank(message = "The name cannot be null or empty")
    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @NotBlank(message = "The company email cannot be null or empty")
    @Email
    @Column(name = "company_email")
    private String company_email;

    @NotBlank(message = "The personal email cannot be null or empty")
    @Email
    @Column(name = "personal_email")
    private String personal_email;

    @NotBlank(message = "The username cannot be null or empty")
    @Column(name = "city")
    private String city;

    @NotNull
    @Column(name = "active")
    private Boolean active = true;

    @NotNull
    @Column(name = "created_date")
    private Date created_date;

    private String image_url;

    private Date termination_date;

}
