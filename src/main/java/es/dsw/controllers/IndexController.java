package es.dsw.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"DataUser"})
public class IndexController {

	@GetMapping(value= {"/","/index"})
	public String idx() {
		
		return "index";
	}
	
	@GetMapping(value= {"/wellcome"})
	public String wellcome() {
		
		return "wellcome";
	}
	
	@GetMapping(value= {"/loggin"})
	public String loggin() {
		
		return "loggin";
	}
	
	@GetMapping(value= {"/ayuda"})
	public String help() {
		
		return "ayuda";
	}
	
}
