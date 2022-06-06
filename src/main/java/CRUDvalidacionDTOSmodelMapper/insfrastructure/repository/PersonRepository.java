package CRUDvalidacionDTOSmodelMapper.insfrastructure.repository;

import CRUDvalidacionDTOSmodelMapper.domain.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, String> {
    List<Person> findByName(String name);
    Optional<Person> findById(String id);
    public void deleteById(String id);
}
