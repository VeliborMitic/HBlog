package net.henryco.hblog.mvc.controllers.acc.admin;

import net.henryco.hblog.mvc.controllers.form.ProfileForm;
import net.henryco.hblog.mvc.model.entity.account.AuthUserProfile;
import net.henryco.hblog.mvc.model.entity.account.BaseUserProfile;
import net.henryco.hblog.mvc.servives.account.ExtendedProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import static net.henryco.hblog.configurations.WebConfiguration.AVATAR_UPLOAD_DIR;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Henry on 06/07/17.
 */
@Controller
@RequestMapping("/account/admin/profiles")
public class AdminProfilesController {

	private final ExtendedProfileService profileService;

	@Autowired
	public AdminProfilesController(ExtendedProfileService profileService) {
		this.profileService = profileService;
	}




	@RequestMapping(method = GET)
	public String manageProfiles(Model model) {

		List<ProfileForm> formList = new LinkedList<>();
		profileService.getBaseProfiles().forEach(baseUserProfile -> {
			AuthUserProfile authProfile = profileService.getAuthProfile(baseUserProfile.getId());
			ProfileForm form = loadProfileForm(new ProfileForm(), baseUserProfile);
			formList.add(loadFormAuth(form, authProfile));
		});

		model.addAttribute("profiles", formList);
		return "profiles";
	}

	private static ProfileForm loadProfileForm(ProfileForm form, BaseUserProfile baseUserProfile) {

		form.setId(baseUserProfile.getId());
		form.setUsername(baseUserProfile.getUserName());
		form.setPosition(baseUserProfile.getPosition());
		form.setEmail(baseUserProfile.getEmail());
		form.setName(baseUserProfile.getFirstName() + " " + baseUserProfile.getLastName());
		return form;
	}

	private static ProfileForm loadFormAuth(ProfileForm form, AuthUserProfile authProfile) {

		form.setRoles(authProfile.getAuthorities());
		form.setEnabled(authProfile.isEnabled());
		return form;
	}






	@RequestMapping(value = "/stat/switch/{id}", method = POST)
	public String switchNewsStat(@PathVariable("id") long id, Authentication authentication) {

		if (authentication.isAuthenticated() &&
				authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			AuthUserProfile authProfile = profileService.getAuthProfile(id);
			authProfile.setEnabled(!authProfile.isEnabled());
			profileService.updateAuthUserProfile(authProfile);
		}
		return "redirect:/account/admin/profiles";
	}





	@RequestMapping(value = "/stat/delete/{id}", method = POST)
	public String deleteProfile(@PathVariable("id") long id, Authentication authentication) {

		if (authentication.isAuthenticated() &&
				authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			try {
				new File(AVATAR_UPLOAD_DIR + profileService.getBaseProfile(id).getIconLink()).deleteOnExit();
			} catch (Exception ignored) {}
			profileService.deleteProfile(id);
		}
		return "redirect:/account/admin/profiles";
	}


}
