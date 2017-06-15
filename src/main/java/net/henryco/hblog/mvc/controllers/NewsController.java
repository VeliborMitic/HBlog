package net.henryco.hblog.mvc.controllers;

import net.henryco.hblog.mvc.servives.PostFormService;
import org.springframework.beans.factory.annotation.Autowired;
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

	private final PostFormService postFormService;

	@Autowired
	public NewsController(PostFormService postFormService) {
		this.postFormService = postFormService;
	}



	@RequestMapping(method = GET)
	public String news() {
		return "redirect:/news/0";
	}


	@RequestMapping(value = "/{numb}", method = GET)
	public String newsPage(@PathVariable("numb") int pageNumb, Model model) {

		if (pageNumb > 0) model.addAttribute("pageNumber", pageNumb);


		return "news";
	}
}
