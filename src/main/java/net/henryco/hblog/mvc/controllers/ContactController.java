package net.henryco.hblog.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Henry on 18/06/17.
 */
@Controller
@RequestMapping("/join")
public class ContactController {


	@RequestMapping(method = GET)
	public String join(Model model) {
		return "contact";
	}

}
