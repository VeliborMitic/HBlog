package net.henryco.hblog.mvc.controllers.acc;


import net.henryco.hblog.mvc.controllers.acc.form.PostForm;
import net.henryco.hblog.mvc.model.entity.post.StandardPostPreview;
import net.henryco.hblog.mvc.servives.post.PostDirectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Henry on 03/07/17.
 */
@Controller
@RequestMapping("/account/profile")
public class UserPostsController {


	private final PostDirectService postDirectService;

	@Autowired
	public UserPostsController(PostDirectService postDirectService) {
		this.postDirectService = postDirectService;
	}

	@RequestMapping(value = "/manage", method = GET)
	public String managePosts(Model model, Principal principal) {

		List<StandardPostPreview> postPreviews = postDirectService.getAllPostsFromAuthor(principal.getName());
		model.addAttribute("posts", postPreviews);
		return "manage";
	}

	@RequestMapping("/manage/delete/{id}")
	public String deletePost(Principal principal, @PathVariable("id") long id) {

		if (postDirectService.getPostById(id).getAuthor().equals(principal.getName()))
			postDirectService.removePostById(id);
		return "redirect:/account/profile/manage";
	}

	@RequestMapping(value = "/addpost", method = GET)
	public String addPost(Principal principal, Model model) {


		return "addpost";
	}

	@RequestMapping(value = "/addpost/submit/post", method = POST)
	public String submitPost(@Valid @ModelAttribute("post_form") PostForm postForm,
							 BindingResult bindingResult,
							 Principal principal) {


		return "redirect:/account/profile";
	}

	@RequestMapping(value = "/addpost/submit/icon", method = POST)
	public String submitIconPreview() {

		return "addpost";
	}

	@RequestMapping(value = "/addpost/submit/resources", method = POST)
	public String submitResources() {

		return "addpost";
	}

}
