package net.henryco.hblog.mvc.controllers.acc;

import net.henryco.hblog.mvc.controllers.acc.form.SettingsForm;
import net.henryco.hblog.mvc.model.entity.account.AuthUserProfile;
import net.henryco.hblog.mvc.model.entity.account.BaseUserProfile;
import net.henryco.hblog.mvc.servives.account.BaseProfileService;
import net.henryco.hblog.mvc.servives.account.ExtendedProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;

import static java.io.File.separator;
import static net.henryco.hblog.configurations.WebConfiguration.REL_FILE_PATH;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Henry on 18/06/17.
 */
@Controller
@RequestMapping("/account/profile")
public class UserProfileController {


	private static final String AVATAR_UPLOAD_DIR = REL_FILE_PATH + separator + "res" +separator + "public" + separator + "av" + separator;
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



	@RequestMapping("/upload/avatar")
	public String upLoadAvatar(@RequestParam("file") MultipartFile file,
							   Principal principal) {

		final int i = file.getOriginalFilename().lastIndexOf(".");
		final String name = principal.getName() + System.currentTimeMillis() + ( i != -1 ? file.getOriginalFilename().substring(i) : "");

		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(AVATAR_UPLOAD_DIR + name);
			Files.write(path, bytes);
		} catch (IOException e) {e.printStackTrace();}

		BaseUserProfile profile = profileService.getUserProfileByUserName(principal.getName());
		profile.setIconLink(name);
		extendedProfileService.updateBaseUserProfile(profile);
		return "redirect:/account/profile";
	}

}