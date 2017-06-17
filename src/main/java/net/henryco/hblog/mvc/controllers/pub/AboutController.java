package net.henryco.hblog.mvc.controllers.pub;

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


	@RequestMapping(method = GET)
	public String about(Model model) {
		return "about";
	}

}
