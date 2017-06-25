package net.henryco.hblog.mvc.controllers.acc;

import net.henryco.hblog.mvc.servives.account.BaseProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Henry on 18/06/17.
 */
@Controller
@RequestMapping("/account/profile")
public class UserProfileController {

	private final BaseProfileService profileService;

	@Autowired
	public UserProfileController(BaseProfileService profileService) {
		this.profileService = profileService;
	}

	@RequestMapping(method = GET)
	public String profile(Model model, Principal principal) {

		model.addAttribute(
				"user_profile",
				profileService.getUserProfileByUserName(principal.getName())
		);
		return "profile";
	}

	@RequestMapping(value = "/edit", method = GET)
	public String edit(Model model) {

		return "edit";
	}

}
