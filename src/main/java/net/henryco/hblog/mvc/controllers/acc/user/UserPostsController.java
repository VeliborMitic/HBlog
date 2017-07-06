package net.henryco.hblog.mvc.controllers.acc.user;

import net.henryco.hblog.mvc.controllers.form.MultiFileForm;
import net.henryco.hblog.mvc.controllers.form.PostForm;
import net.henryco.hblog.mvc.model.entity.account.AuthUserProfile;
import net.henryco.hblog.mvc.model.entity.account.BaseUserProfile;
import net.henryco.hblog.mvc.model.entity.post.StandardPostContent;
import net.henryco.hblog.mvc.model.entity.post.StandardPostPreview;
import net.henryco.hblog.mvc.servives.account.ExtendedProfileService;
import net.henryco.hblog.mvc.servives.post.PostDirectService;
import net.henryco.hblog.utils.Utils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.henryco.hblog.configurations.WebConfiguration.DEF_PATH;
import static net.henryco.hblog.configurations.WebConfiguration.UPLOAD_PATH;
import static net.henryco.hblog.utils.Utils.loadPostForm;
import static net.henryco.hblog.utils.Utils.saveMultiPartFileWithNewName;
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

		if (postDirectService.getPostPreviewById(id).getAuthor().equals(principal.getName()))
			postDirectService.removePostById(id);
		return "redirect:/account/profile/manage";
	}



	@RequestMapping(value = "/addpost", method = GET)
	public String addPost(Model model) {

		PostForm form = new PostForm();
		form.setContent("For attach images use <img/> and {res::[number of attached image]} like this:\n <img src=\"{res::[1]}\"/>");
		model.addAttribute("post_form", form);
		model.addAttribute("attached_res", new MultiFileForm());
		return "addpost";
	}




	@RequestMapping(value = "/editpost/{id}", method = GET)
	public String edit(@PathVariable("id") long id, Model model, Principal principal) {

		StandardPostPreview postPreview = postDirectService.getPostPreviewById(id);
		if (postPreview.getAuthor().equals(principal.getName())) {
			PostForm postForm = loadPostForm(new PostForm(), postPreview);
			postForm.setContent(postDirectService.getPostContentById(id).getContent());
			model.addAttribute("post_form", postForm);
			model.addAttribute("attached_res", new MultiFileForm());
			return "addpost";
		}

		return "redirect:/account/profile";
	}


}
