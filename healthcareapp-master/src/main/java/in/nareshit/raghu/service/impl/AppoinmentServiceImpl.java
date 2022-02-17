package in.nareshit.raghu.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nareshit.raghu.entity.Appoinment;
import in.nareshit.raghu.exception.AppoinmentNotFoundException;
import in.nareshit.raghu.repo.AppoinmentRepository;
import in.nareshit.raghu.service.IAppoinmentService;

/**
 * @author:RAGHU SIR 
 *  Generated F/w:SHWR-Framework 
 */
@Service
public class AppoinmentServiceImpl implements IAppoinmentService {
	
	@Autowired
	private AppoinmentRepository repo;

	public Long saveAppoinment(Appoinment appoinment) {
		return repo.save(appoinment).getId();
	}

	public void updateAppoinment(Appoinment appoinment) {
		if(appoinment.getId()!=null && repo.existsById(appoinment.getId())) {
			repo.save(appoinment);
		} else {
			throw new AppoinmentNotFoundException("Appoinment '"+appoinment.getId()+"' not exist");
		}
	}

	public void deleteAppoinment(Long id) {
		//repo.deleteById(id);
		repo.delete(getOneAppoinment(id));
	}

	public Appoinment getOneAppoinment(Long id) {
		//return repo.findById(id).get();
		Optional<Appoinment> opt = repo.findById(id);
		if(opt.isEmpty()) {
			throw new AppoinmentNotFoundException("Appoinment '"+id+"' not exist");
		} else {
			return opt.get();
		}
	}


	public List<Appoinment> getAllAppoinments() {
		return repo.findAll();
	}
}
