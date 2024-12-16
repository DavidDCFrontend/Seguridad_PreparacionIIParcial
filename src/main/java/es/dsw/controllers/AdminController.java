package es.dsw.controllers;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import es.dsw.app.SecurityConfiguration;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/admin/")
public class AdminController {

	@GetMapping(value= {"/"})
	public String admin() {
		
		return "views/admin";
	}
	
	@PostMapping(value= {"/newuser"})
	public String newuser(HttpServletRequest request,
			              @RequestParam(name="user", defaultValue="") String usuario,
						  @RequestParam(name="pass", defaultValue="") String password,
						  CsrfToken csrfToken 
			) {
		
		String csrfTokenValue = csrfToken.getToken();
		 
		if (usuario.trim().equals("") || password.trim().equals("")) {
			return "redirect:/admin?errorcreated&_csrf="+csrfTokenValue;
		} else {
			@SuppressWarnings("deprecation")
			UserDetails user1 = User.withDefaultPasswordEncoder()
					                .username(usuario)
					                .password(password)
					                .build();
			
			SecurityConfiguration.InMemory.createUser(user1);
			
			try {
				request.logout();
				request.login(usuario, password);
				
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "redirect:/index";
		}
		
	}
	
}
