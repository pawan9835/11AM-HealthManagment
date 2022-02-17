package in.nareshit.raghu.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.nareshit.raghu.entity.Specialization;

public interface SpecializationRepository 
	extends JpaRepository<Specialization, Long>
{

	@Query("SELECT specId, specName FROM Specialization ")
	List<Object[]> getSpecializationIdAndName();
}
