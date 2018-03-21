package net.henryco.hblog.mvc.controllers.pub;

import net.henryco.hblog.mvc.servives.account.BaseProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Henry on 18/06/17.
 */
@Controller
@RequestMapping("/about")
public class AboutController {

	private final BaseProfileService profileService;

	@Autowired
	public AboutController(BaseProfileService profileService) {
		this.profileService = profileService;
	}

	@RequestMapping(method = GET)
	public String about(Model model) {
		model.addAttribute("profiles", profileService.getAllProfiles());
		return "about";
	}

}
