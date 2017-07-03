package net.henryco.hblog.mvc.controllers.acc;

import net.henryco.hblog.mvc.controllers.acc.form.SettingsForm;
import net.henryco.hblog.mvc.model.dto.account.AuthUserProfile;
import net.henryco.hblog.mvc.model.dto.account.BaseUserProfile;
import net.henryco.hblog.mvc.servives.account.BaseProfileService;
import net.henryco.hblog.mvc.servives.account.ExtendedProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Henry on 18/06/17.
 */
@Controller
@RequestMapping("/account/profile")
public class UserProfileController {

	private final BaseProfileService profileService;
	private final ExtendedProfileService extendedProfileService;

	@Autowired
	public UserProfileController(BaseProfileService profileService, ExtendedProfileService extendedProfileService) {
		this.profileService = profileService;
		this.extendedProfileService = extendedProfileService;
	}

	@RequestMapping(method = GET)
	public String profile(Model model, Principal principal) {

		model.addAttribute("user_profile", profileService.getUserProfileByUserName(principal.getName()));
		return "profile";
	}

	@RequestMapping(value = "/settings", method = GET)
	public String edit(Model model, Principal principal) {

		SettingsForm settingsForm = new SettingsForm();
		BaseUserProfile profile = profileService.getUserProfileByUserName(principal.getName());
		settingsForm.setEmail(profile.getEmail());
		settingsForm.setFirstName(profile.getFirstName());
		settingsForm.setLastName(profile.getLastName());
		settingsForm.setPosition(profile.getPosition());

		model.addAttribute("settingsForm", settingsForm);
		return "settings";
	}

	@RequestMapping(value = "/settings/save", method = POST)
	public String registration(@Valid @ModelAttribute SettingsForm form,
							   BindingResult bindingResult, Principal principal) {

		if (bindingResult.hasErrors()) return "settings";
		BaseUserProfile profile = extendedProfileService.getBaseProfileByNameOrEmail(principal.getName());

		if (!profile.getEmail().equals(form.getEmail())
				&& extendedProfileService.getBaseProfileByNameOrEmail(form.getEmail()) != null) {
			bindingResult.addError(new ObjectError("e_mail", "Email already exists"));
			return "settings";
		}

		if (form.getPassword() != null && !form.getPassword().isEmpty()) {

			if (form.getPassword().length() < 8) {
				bindingResult.addError(new ObjectError("pass", "Password min size is 8"));
				return "settings";
			}

			AuthUserProfile authUserProfile = extendedProfileService.getAuthProfileReference(profile.getId());
			authUserProfile.setPassword(form.getPassword());
			extendedProfileService.updateAuthUserProfile(authUserProfile);
		}

		profile.setEmail(form.getEmail());
		profile.setFirstName(form.getFirstName());
		profile.setLastName(form.getLastName());
		profile.setPosition(form.getPosition());

		extendedProfileService.updateBaseUserProfile(profile);

		return "redirect:/account/profile/settings";
	}



}
