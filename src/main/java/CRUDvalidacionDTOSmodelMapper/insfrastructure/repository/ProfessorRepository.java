package CRUDvalidacionDTOSmodelMapper.insfrastructure.repository;

import CRUDvalidacionDTOSmodelMapper.domain.professor.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, String> {
    public Optional<Professor> findById(String id);
    public void deleteById(String id);
}
