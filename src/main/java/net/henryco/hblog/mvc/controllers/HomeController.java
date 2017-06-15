package net.henryco.hblog.mvc.controllers;

import net.henryco.hblog.mvc.model.StandardPost;
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
		if (posts == null || posts.size() == 0) return "index";
		int i = 0;
		for (StandardPost post: posts) {

			model.addAttribute("post_id"+i, post.getId());
			model.addAttribute("lastNewsPreview_"+i, post.getPreview());
			model.addAttribute("lastNewsTittle_"+i, post.getTitle());
			i += 1;
		}

		StandardPost latest = postFormService.getLatestPost();
		if (latest != null) System.out.println(latest);

		return "index";
	}



	@RequestMapping(path = "/index", method = GET)
	public String index() {
		return "redirect:/";
	}

}
