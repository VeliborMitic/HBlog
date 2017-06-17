package net.henryco.hblog.mvc.controllers.acc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Henry on 18/06/17.
 */
@Controller
@RequestMapping("/profile")
public class ProfileController {

	@RequestMapping(method = GET)
	public String profile(Model model) {

		return "profile";
	}

}
