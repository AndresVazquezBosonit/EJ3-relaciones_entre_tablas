package CRUDvalidacionDTOSmodelMapper.insfrastructure.repository;

import CRUDvalidacionDTOSmodelMapper.domain.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, String> {
    Optional<Student> findById(String id);
}
