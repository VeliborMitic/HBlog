package net.henryco.hblog.mvc.controllers;

import net.henryco.hblog.mvc.servives.ArticlePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Henry on 17/06/17.
 */
@Controller
@RequestMapping("/news/article")
public class ArticleController {

	private final ArticlePageService articlePageService;

	@Autowired
	public ArticleController(ArticlePageService articlePageService) {
		this.articlePageService = articlePageService;
	}


	@RequestMapping("/")
	public String news() {
		return "redirect:/news/";
	}

	@RequestMapping(value = "/{id}", method = GET)
	public String article(@PathVariable("id") long id, Model model) {

		model.addAttribute("post_title", articlePageService.getPostTitle(id));


		return "article";
	}

}
