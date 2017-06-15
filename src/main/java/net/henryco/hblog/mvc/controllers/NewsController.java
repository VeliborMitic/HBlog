package net.henryco.hblog.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Henry on 15/06/17.
 */
@Controller
@RequestMapping("/news")
public class NewsController {

	@RequestMapping(method = GET)
	public String news() {
		return "redirect:/news/page/0";
	}


	@RequestMapping(value = "/page/{numb}", method = GET)
	public String newsPage(@PathVariable("numb") int pageNumb, Model model) {

		return "news";
	}
}
