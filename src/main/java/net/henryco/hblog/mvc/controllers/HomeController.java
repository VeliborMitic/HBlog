package net.henryco.hblog.mvc.controllers;

import net.henryco.hblog.mvc.projs.StandardPost;
import net.henryco.hblog.mvc.servives.PostFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Henry on 14/06/17.
 */
@Controller
@RequestMapping("/")
public class HomeController {

	private final PostFormService postFormService;

	@Autowired
	public HomeController(PostFormService postFormService) {
		this.postFormService = postFormService;
	}

	@RequestMapping(method = GET)
	public String home(Model model) {

		List<StandardPost> posts = postFormService.getLastPosts(4);
		int i = 0;
		for (StandardPost post: posts) {

			String content = post.getContent();
			content = content.length() > 185 ? content.substring(0, 185) : content;
			model.addAttribute("lastNewsContent_"+i, content);
			model.addAttribute("lastNewsTittle_"+i, post.getTitle());
			i += 1;
		}
		return "index";
	}

	@RequestMapping(path = "/index", method = GET)
	public String index() {
		return "redirect:/";
	}

}
