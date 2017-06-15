package net.henryco.hblog.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Henry on 15/06/17.
 */
@Controller
@RequestMapping("/news")
public class NewsController {

	@RequestMapping(method = GET)
	public String news(Model model) {

		return "page";
	}
}
