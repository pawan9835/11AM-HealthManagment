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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.nareshit.raghu.entity.Appoinment;
import in.nareshit.raghu.exception.AppoinmentNotFoundException;
import in.nareshit.raghu.service.IAppoinmentService;
import in.nareshit.raghu.service.IDoctorService;

/**
 * @author:RAGHU SIR 
 *  Generated F/w:SHWR-Framework 
 */
@Controller
@RequestMapping("/appoinment")
public class AppoinmentController {
	
	@Autowired
	private IAppoinmentService service;
	
	@Autowired
	private IDoctorService doctorService; //HAS-A

	private void commonUi(Model model) {
		//Read Map from Child service and send to Parent UI
		model.addAttribute("doctors", doctorService.getDocIdAndNames());
	}
	
	@GetMapping("/register")
	public String registerAppoinment(Model model) {
		model.addAttribute("appoinment",new Appoinment());
		commonUi(model);
		return "AppoinmentRegister";
	}

	@PostMapping("/save")
	public String saveAppoinment(@ModelAttribute Appoinment appoinment, Model model) {
		java.lang.Long id=service.saveAppoinment(appoinment);
		model.addAttribute("message","Appoinment created with Id:"+id);
		model.addAttribute("appoinment",new Appoinment()) ;
		commonUi(model);
		return "AppoinmentRegister";
	}

	@GetMapping("/all")
	public String getAllAppoinments(Model model,
			@RequestParam(value = "message", required = false) String message) {
		List<Appoinment> list=service.getAllAppoinments();
		model.addAttribute("list",list);
		model.addAttribute("message",message);
		return "AppoinmentData";
	}

	@GetMapping("/delete")
	public String deleteAppoinment(@RequestParam Long id, RedirectAttributes attributes) {
		try {
			service.deleteAppoinment(id);
			attributes.addAttribute("message","Appoinment deleted with Id:"+id);
		} catch(AppoinmentNotFoundException e) {
			attributes.addAttribute("message",e.getMessage());
		}
		return "redirect:all";
	}

	@GetMapping("/edit")
	public String editAppoinment(@RequestParam Long id, Model model, RedirectAttributes attributes) {
		String page=null;
		try {
			Appoinment ob=service.getOneAppoinment(id);
			model.addAttribute("appoinment",ob);
			commonUi(model);
			page="AppoinmentEdit";
		} catch(AppoinmentNotFoundException e) {
			attributes.addAttribute("message",e.getMessage());
			page="redirect:all";
		}
		return page;
	}

	@PostMapping("/update")
	public String updateAppoinment(@ModelAttribute Appoinment appoinment,
			RedirectAttributes attributes) {
		service.updateAppoinment(appoinment);
		attributes.addAttribute("message","Appoinment updated");
		return "redirect:all";
	}
}
