package in.nareshit.raghu.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nareshit.raghu.entity.Doctor;
import in.nareshit.raghu.exception.DoctorNotFoundException;
import in.nareshit.raghu.repo.DoctorRepository;
import in.nareshit.raghu.service.IDoctorService;
import in.nareshit.raghu.util.AppUtil;

@Service
public class DoctorServiceImpl implements IDoctorService {

	@Autowired
	private DoctorRepository repo;
	
	public Long saveDoctor(Doctor doc) {
		return repo.save(doc).getDocId();
	}

	public List<Doctor> getAllDoctors() {
		return repo.findAll();
	}

	public Doctor getOneDoctor(Long id) {
		//return repo.findById(id).get();
		Optional<Doctor> opt = repo.findById(id);
		if(opt.isEmpty()) {
			throw new DoctorNotFoundException("Doctor '"+id+"' not exist");
		} else {
			return opt.get();
		}
	}

	@Override
	public void deleteDoctor(Long id) {
		//repo.deleteById(id);
		repo.delete(getOneDoctor(id));
	}

	@Override
	public void updateDoctor(Doctor doc) {
		if(doc.getDocId()!=null && repo.existsById(doc.getDocId()))	{
			repo.save(doc);//update
		} else {
			throw new DoctorNotFoundException("Doctor '"+doc.getDocId()+"' not exist");
		}
	}
	
	@Override
	public Map<Long, String> getDocIdAndNames() {
		List<Object[]> list = repo.getDocIdAndNames();
		return AppUtil.convertListToMap(list);
	}
	
	@Override
	public List<Object[]> getDocotrsBySpecialization() {
		return repo.getDocotrsBySpecialization();
	}

}
