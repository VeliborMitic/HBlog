package net.henryco.hblog.mvc.controllers.acc;

import net.henryco.hblog.mvc.controllers.acc.form.LoginForm;
import net.henryco.hblog.mvc.controllers.acc.form.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Henry on 18/06/17.
 */
@Controller
public class SessionController {

	private final Environment environment;

	@Autowired
	public SessionController(Environment environment) {
		this.environment = environment;
	}


//	--------- LOGIN PART ------------------------------------------------------------

	@RequestMapping(value = "/login", method = GET)
	public String login() {
		return "redirect:/acc/login";
	}

	@RequestMapping(value = "/acc/login", method = GET)
	public String login(Model model) {
		model.addAttribute("loginForm", new LoginForm());
		return "login";
	}

	@RequestMapping(value = "/acc/login/finish", method = POST)
	public String login(@ModelAttribute @Valid LoginForm loginForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) return "login";
		//TODO verification
		return "redirect:/account";
	}





//	--------- REGISTRATION PART ------------------------------------------------------

	@RequestMapping(value = "/registration", method = GET)
	public String registration() {
		return "redirect:/acc/registration";
	}

	@RequestMapping(value = "/acc/registration", method = GET)
	public String registration(Model model) {
		model.addAttribute("registrationForm", new RegistrationForm());
		return "registration";
	}

	@RequestMapping(value = "/acc/registration/finish")
	public String registration(
			@Valid @ModelAttribute RegistrationForm registrationForm,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) return "registration";
		System.out.println(registrationForm);

		environment.getProperty("admin_pub_pgp_key");

		// TODO: 18/06/17
		return "redirect:/acc/login";
	}

}
