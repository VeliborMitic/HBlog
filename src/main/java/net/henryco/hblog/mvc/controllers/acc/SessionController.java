package net.henryco.hblog.mvc.controllers.acc;

import net.henryco.hblog.mvc.controllers.acc.form.RegistrationForm;
import net.henryco.hblog.mvc.model.account.BaseUserProfile;
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

/**
 * @author Henry on 18/06/17.
 */
@Controller
@PropertySource("static/props/base.properties")
public class SessionController {

	private static final String[] ROLES_ADMIN = {"ROLE_USER", "ROLE_ADMIN"};
	private static final String[] ROLES_USER = {"ROLE_USER"};

	private final Environment environment;
	private final ExtendedProfileService profileService;

	@Autowired
	public SessionController(Environment environment,
							 ExtendedProfileService profileService) {
		this.environment = environment;
		this.profileService = profileService;
	}



	//	--------- ACCOUNT PART ----------------------------------------------------------
	@RequestMapping(value = "/account", method = GET)
	public String account() {
		// TODO: 19/06/17
		return "redirect:/";
	}



	//	--------- LOGIN PART ------------------------------------------------------------
	@RequestMapping(value = "/login", method = GET)
	public String login() {
		return "redirect:/acc/login";
	}




	//	--------- REGISTRATION PART -----------------------------------------------------
	@RequestMapping(value = "/acc/registration/finish")
	public String registration(@Valid @ModelAttribute RegistrationForm form,
							   BindingResult bindingResult) {

		if (bindingResult.hasErrors()) return "registration";

		boolean r_user = form.getGpgPubKey().equals(environment.getProperty("access.keys.gpg.user"));
		boolean r_admin = form.getGpgPubKey().equals(environment.getProperty("access.keys.gpg.admin"));

		if (!r_user && !r_admin) {
			bindingResult.addError(new ObjectError("gpg_key", "GPG KEY is invalid"));
			return "registration";
		}

		BaseUserProfile existing = profileService.getBaseProfileByNameOrEmail(form.getUserName());
		if (existing != null) {
			bindingResult.addError(new ObjectError("e_mail", "Username or email already exists"));
			return "registration";
		}

		BaseUserProfile profile = new BaseUserProfile();
		profile.setFirstName(form.getFirstName());
		profile.setLastName(form.getLastName());
		profile.setUserName(form.getUserName());
		profile.setEmail(form.getEmail());

		profileService.saveNewBaseUserProfile(profile, form.getPassword(), r_admin ? ROLES_ADMIN : ROLES_USER);

		return "redirect:/acc/login";
	}


	@RequestMapping(value = "/acc/registration", method = GET)
	public String registration(Model model) {
		model.addAttribute("registrationForm", new RegistrationForm());
		return "registration";
	}

	@RequestMapping(value = "/registration", method = GET)
	public String registration() {
		return "redirect:/acc/registration";
	}


}
