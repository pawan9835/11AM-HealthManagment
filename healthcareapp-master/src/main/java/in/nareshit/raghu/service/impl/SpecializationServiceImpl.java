package in.nareshit.raghu.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nareshit.raghu.entity.Specialization;
import in.nareshit.raghu.exception.SpecializationNotFoundException;
import in.nareshit.raghu.repo.SpecializationRepository;
import in.nareshit.raghu.service.ISpecializationService;
import in.nareshit.raghu.util.AppUtil;

@Service
public class SpecializationServiceImpl 
	implements ISpecializationService
{

	@Autowired
	private SpecializationRepository repo; //HAS-A
	
	@Override
	public Long saveSpecialization(Specialization obj) {
		obj = repo.save(obj); //INSERT
		return obj.getSpecId();//PK
	}
	
	@Override
	public List<Specialization> getAllSpecializations() {
		List<Specialization> list =  repo.findAll();
		return list;
	}
	
	@Override
	public void deleteSpecialization(Long id) {
		//repo.deleteById(id);
		repo.delete(getOneSpecialization(id));
	}
	
	@Override
	public Specialization getOneSpecialization(Long id) {
		Optional<Specialization> opt = repo.findById(id);
		if(opt.isEmpty()) {
			throw new SpecializationNotFoundException(
					"Specialization '"+id+"' not exist");
		} else {
			return opt.get();
		}
	}
	
	@Override
	public void updateSpecialization(Specialization obj) {
		if(obj.getSpecId()!=null && repo.existsById(obj.getSpecId())) {
			repo.save(obj);
		} else {
			throw new SpecializationNotFoundException(
					"Specialization '"+obj.getSpecId()+"' not exist");
		}
	}
	
	@Override
	public Map<Long, String> getSpecializationIdAndName() {
		List<Object[]> list = repo.getSpecializationIdAndName();
		return AppUtil.convertListToMap(list);
	}
}
