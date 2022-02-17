package in.nareshit.raghu.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.nareshit.raghu.entity.Doctor;
import in.nareshit.raghu.exception.DoctorNotFoundException;
import in.nareshit.raghu.service.IDoctorService;
import in.nareshit.raghu.service.ISpecializationService;
import in.nareshit.raghu.util.DoctorUtil;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	private IDoctorService service;
	
	@Autowired
	private ISpecializationService specializationService;
	
	@Autowired
	private ServletContext context;

	@Autowired
	private DoctorUtil util;
	
	private void commonUi(Model model) {
		model.addAttribute("specializations", specializationService.getSpecializationIdAndName());
	}
	
	//1. show Register page
	@GetMapping("/register")
	public String showReg(Model model) {
		commonUi(model);
		return "DoctorRegister";
	}
	
	//2. Save Form data
	@PostMapping("/save")
	public String save(
			@ModelAttribute Doctor doctor,
			Model model)
	{
		Long id =  service.saveDoctor(doctor);
		String message = "Doctor '"+id+"' created";
		model.addAttribute("message", message);
		commonUi(model);
		return "DoctorRegister";
	}
	
	//3. show data Page
	@GetMapping("/all")
	public String showAll(
			Model model,
			@RequestParam(required = false)String message
			) 
	{
		List<Doctor> list = service.getAllDoctors();
		model.addAttribute("list", list);
		model.addAttribute("message", message);
		return "DoctorData";
	}
	
	//4. Delete by id
	@GetMapping("/delete")
	public String delete(
			@RequestParam Long id,
			RedirectAttributes attributes
			)
	{
		try {
			service.deleteDoctor(id);
			attributes.addAttribute("message", "Doctor '"+id+"' deleted");
		} catch (DoctorNotFoundException dnfe) {
			attributes.addAttribute("message", dnfe.getMessage());
		}
		return "redirect:all";
	}
	
	//5. show data in edit page
	@GetMapping("/edit")
	public String showEdit(
			@RequestParam Long id,
			Model model,
			RedirectAttributes attributes
			) 
	{
		String page = null;
		try {
			Doctor doc =  service.getOneDoctor(id);
			model.addAttribute("doctor", doc);
			commonUi(model);
			page = "DoctorEdit";
		} catch (DoctorNotFoundException dnfe) {
			attributes.addAttribute("message", dnfe.getMessage());
			page ="redirect:all";
		}
		return page;
	}
	
	//6. update on submit
	@PostMapping("/update")
	public String update(
			@ModelAttribute Doctor doctor,
			RedirectAttributes attributes
			)
	{
		service.updateDoctor(doctor);
		attributes.addAttribute("message", "DOCTOR '"+doctor.getDocId()+"' UPDATED");
		return "redirect:all";
	}
	
	//7. show charts
	@GetMapping("/charts")
	public String showCharts() {
		List<Object[]> list = service.getDocotrsBySpecialization();
		String path = context.getRealPath("/"); //server root location
		util.generatePie(path,list);
		util.generateBar(path,list);
		
		return "DoctorCharts";
	}
} 
