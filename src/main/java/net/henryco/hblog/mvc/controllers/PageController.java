package net.henryco.hblog.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Henry on 17/06/17.
 */
@Controller
@RequestMapping("/news/article")
public class PageController {



	@RequestMapping(value = "/{id}", method = GET)
	public String article(@PathVariable("id") long id) {
		return "page";
	}

}
