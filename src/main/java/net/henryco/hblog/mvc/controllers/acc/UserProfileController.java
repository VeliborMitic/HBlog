package net.henryco.hblog.mvc.controllers.acc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Henry on 18/06/17.
 */
@Controller
@RequestMapping("/account/profile")
public class UserProfileController {

	@RequestMapping(method = GET)
	public String profile(Model model) {

		return "account";
	}

}
