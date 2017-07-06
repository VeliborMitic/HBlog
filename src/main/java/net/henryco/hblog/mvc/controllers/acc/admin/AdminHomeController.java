package net.henryco.hblog.mvc.controllers.acc.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Henry on 19/06/17.
 */
@Controller
@RequestMapping("/account/admin")
public class AdminHomeController {


	private final Environment environment;

	@Autowired
	public AdminHomeController(Environment environment) {
		this.environment = environment;
	}



	@RequestMapping(method = GET)
	public String mainPanel(Model model, Authentication authentication) {

		if (authentication.isAuthenticated() &&
				authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			model.addAttribute("user_gpg", environment.getProperty("access.keys.gpg.user"));
			model.addAttribute("admin_gpg", environment.getProperty("access.keys.gpg.admin"));
		}
		return "admin";
	}



}