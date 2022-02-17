package in.nareshit.raghu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.nareshit.raghu.entity.Specialization;
import in.nareshit.raghu.exception.SpecializationNotFoundException;
import in.nareshit.raghu.service.ISpecializationService;
import in.nareshit.raghu.view.SpecializationExcelView;

@Controller
@RequestMapping("/spec")
public class SpecializationController {

	@Autowired
	private ISpecializationService service;//HAS-A

	//1. Show Register Page
	@GetMapping("/register")
	public String showReg() {
		return "SpecializationRegister";
	}

	//2. save
	@PostMapping("/save")
	public String save(
			//reading Form data
			@ModelAttribute Specialization specialization,
			Model model
			)
	{
		//calling service
		Long id = service.saveSpecialization(specialization);

		//creating message 
		String message = "SPECIALIZATION '"+id+"' CREATED";

		//sending message to UI
		model.addAttribute("message", message);

		//goto FORM Page
		return "SpecializationRegister";
	}

	//3. fetch and display
	@GetMapping("/all")
	public String showData(
			Model model,
			@RequestParam(required = false) String message
			)
	{
		//call service
		List<Specialization> list = service.getAllSpecializations();
		//send data to UI
		model.addAttribute("list", list);
		model.addAttribute("message", message);

		//goto HTML Page
		return "SpecializationData";
	}


	//4. remove
	@GetMapping("/delete")
	public String delete(
			@RequestParam Long id,
			RedirectAttributes attributes
			) 
	{
		try {
			service.deleteSpecialization(id);
			attributes.addAttribute("message", "Specialization '"+id+"' Deleted");
		} catch (SpecializationNotFoundException snfe) {
			attributes.addAttribute("message", snfe.getMessage());
		}
		return "redirect:all";
	}

	//5. show edit page
	@GetMapping("/edit")
	public String showEdit(
			@RequestParam Long id,
			Model model,
			RedirectAttributes attributes
			) 
	{
		String page = null;
		try {
			//call service
			Specialization obj =  service.getOneSpecialization(id);
			model.addAttribute("specialization", obj);
			//Goto Edit HTML Page
			page = "SpecializationEdit";

		} catch (SpecializationNotFoundException snfe) {
			attributes.addAttribute("message", snfe.getMessage());
			page = "redirect:all";
		}

		return page;
	}

	//6. update data
	@PostMapping("/update")
	public String update(
			//Read Form data 
			@ModelAttribute Specialization specialization,
			RedirectAttributes attributes
			)
	{
		//call service
		service.updateSpecialization(specialization);
		attributes.addAttribute("message", "specialization '"+specialization.getSpecId()+"' Updated Success");
		//goto Data Page back
		return "redirect:all";
	}

	//7. Excel Export
	@GetMapping("/excel")
	public ModelAndView excelExport() {
		//Create MAV obj 
		ModelAndView m = new ModelAndView();

		//provide view class object
		m.setView(new SpecializationExcelView());

		//Read data from DB and send to View class
		List<Specialization> list = service.getAllSpecializations();
		m.addObject("list", list);

		return m;
	}
}
