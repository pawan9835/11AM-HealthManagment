package in.nareshit.raghu.service;

import java.util.List;
import java.util.Map;

import in.nareshit.raghu.entity.Doctor;

public interface IDoctorService {

	Long saveDoctor(Doctor doc);
	List<Doctor> getAllDoctors();
	Doctor getOneDoctor(Long id);
	void deleteDoctor(Long id);
	void updateDoctor(Doctor doc);
	
	Map<Long,String> getDocIdAndNames();
	List<Object[]> getDocotrsBySpecialization();
}
