package net.henryco.hblog.mvc.controllers.acc;


import net.henryco.hblog.mvc.model.entity.post.StandardPostPreview;
import net.henryco.hblog.mvc.servives.post.PostDirectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Henry on 03/07/17.
 */
@Controller
@RequestMapping("/account/profile/manage")
public class UserPostsController {


	private final PostDirectService postDirectService;

	@Autowired
	public UserPostsController(PostDirectService postDirectService) {
		this.postDirectService = postDirectService;
	}

	@RequestMapping(method = GET)
	public String managePosts(Model model, Principal principal) {

		List<StandardPostPreview> postPreviews = postDirectService.getAllPostsFromAuthor(principal.getName());
		model.addAttribute("posts", postPreviews);
		return "manage";
	}

	@RequestMapping("/delete/{id}")
	public String deletePost(Principal principal, @PathVariable("id") long id) {

		if (postDirectService.getPostById(id).getAuthor().equals(principal.getName()))
			postDirectService.removePostById(id);
		return "redirect:/account/profile/manage";
	}

}
