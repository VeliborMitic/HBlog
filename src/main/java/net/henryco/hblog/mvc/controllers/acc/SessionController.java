package net.henryco.hblog.mvc.controllers.acc;

import net.henryco.hblog.mvc.controllers.acc.form.LoginForm;
import net.henryco.hblog.mvc.controllers.acc.form.RegistrationForm;
import net.henryco.hblog.mvc.model.account.BaseUserProfile;
import net.henryco.hblog.mvc.model.account.ProfilePassword;
import net.henryco.hblog.mvc.servives.account.ExtendedProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Henry on 18/06/17.
 */
@Controller
@PropertySource("static/props/base.properties")
public class SessionController {

	private final Environment environment;
	private final ExtendedProfileService profileService;

	@Autowired
	public SessionController(Environment environment,
							 ExtendedProfileService profileService) {
		this.environment = environment;
		this.profileService = profileService;
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

		BaseUserProfile profile = profileService.getProfileByNameOrEmail(loginForm.getUserName());
		if (profile == null) {
			bindingResult.addError(new ObjectError("username", "Invalid username or password"));
			return "login";
		}
		ProfilePassword password = profileService.getProfilePassword(profile.getId());
		if (password.getPassword() != null && !password.getPassword().equals(loginForm.getPassword())) {
			bindingResult.addError(new ObjectError("pass", "Invalid username or password"));
			return "login";
		}

		return "redirect:/account/"+profile.getId();
	}







//	--------- REGISTRATION PART ----------------------------------------------------------

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
	public String registration(@Valid @ModelAttribute RegistrationForm form,
							   BindingResult bindingResult) {

		if (bindingResult.hasErrors()) return "registration";
		if (!form.getGpgPubKey().equals(environment.getProperty("access.keys.gpg"))) {
			bindingResult.addError(new ObjectError("pgp_key", "GPG KEY is invalid"));
			return "registration";
		}

		BaseUserProfile existing = profileService.getProfileByNameOrEmail(form.getUserName());
		if (existing != null) {
			bindingResult.addError(new ObjectError("e_mail", "Username or email already exists"));
			return "registration";
		}

		BaseUserProfile profile = new BaseUserProfile();
		profile.setFirstName(form.getFirstName());
		profile.setLastName(form.getLastName());
		profile.setUserName(form.getUserName());
		profile.setEmail(form.getEmail());

		profileService.saveUserProfile(profile, form.getPassword());

		return "redirect:/acc/login";
	}

}
