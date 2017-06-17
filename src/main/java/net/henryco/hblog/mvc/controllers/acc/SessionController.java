package net.henryco.hblog.mvc.controllers.acc;

import net.henryco.hblog.mvc.controllers.acc.form.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Henry on 18/06/17.
 */
@Controller
public class SessionController {

	@RequestMapping(value = "/login", method = GET)
	public String login() {
		return "redirect:/acc/login";
	}

	@RequestMapping(value = "/acc/login", method = GET)
	public String login(Model model) {
		model.addAttribute("loginForm", new LoginForm());
		return "login";
	}

	@RequestMapping(value = "/acc/login", method = POST)
	public String login(@ModelAttribute LoginForm loginForm) {

		return "redirect:/profile";
	}




	@RequestMapping(value = "/acc/registration", method = GET)
	public String registration(Model model) {
		return "registration";
	}

}
