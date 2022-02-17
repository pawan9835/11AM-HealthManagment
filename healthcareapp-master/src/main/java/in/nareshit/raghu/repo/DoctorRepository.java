package in.nareshit.raghu.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.nareshit.raghu.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

	@Query("SELECT doc.docId, doc.docName FROM Doctor doc")
	List<Object[]> getDocIdAndNames();
	
	@Query("SELECT spt.specName,COUNT(spt.specName) FROM Doctor doc INNER JOIN doc.specialization as spt GROUP BY spt.specName")
	List<Object[]> getDocotrsBySpecialization();
}
