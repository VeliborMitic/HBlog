package net.henryco.hblog.mvc.controllers;

import net.henryco.hblog.mvc.model.StandardPostPreview;
import net.henryco.hblog.mvc.servives.NewsPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Henry on 15/06/17.
 */
@Controller
@RequestMapping("/news")
public class NewsController {

	private static final long NEWS_ON_PAGE = 6;

	private final NewsPageService newsPageService;

	@Autowired
	public NewsController(NewsPageService newsPageService) {
		this.newsPageService = newsPageService;
	}



	@RequestMapping(method = GET)
	public String news() {
		return "redirect:/news/1";
	}


	@RequestMapping(value = "/{numb}", method = GET)
	public String newsPage(@PathVariable("numb") long pageNumb, Model model) {

		long newsCount = newsPageService.getNewsCount();
		long maxPages = (long) Math.ceil((double)newsCount / (double)NEWS_ON_PAGE);

		if (pageNumb > maxPages) return "redirect:/news/"+maxPages;
		if (pageNumb <= 0) return news();
		if (pageNumb > 1) model.addAttribute("pageNumber", pageNumb);

		List<StandardPostPreview> posts = newsPageService.getLastPostsInRange(pageNumb - 1, NEWS_ON_PAGE);
		model.addAttribute("posts", posts);

		return "news";
	}
}
