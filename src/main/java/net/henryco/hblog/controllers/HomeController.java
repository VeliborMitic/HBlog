package net.henryco.hblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Henry on 14/06/17.
 */
@Controller
@RequestMapping("/")
public class HomeController {

	@RequestMapping(method = GET)
	public String index() {
		return "index";
	}
}
