package net.henryco.hblog.mvc.controllers.acc;


import net.henryco.hblog.mvc.controllers.acc.form.MultiFileForm;
import net.henryco.hblog.mvc.controllers.acc.form.PostForm;
import net.henryco.hblog.mvc.model.entity.post.StandardPostContent;
import net.henryco.hblog.mvc.model.entity.post.StandardPostPreview;
import net.henryco.hblog.mvc.servives.post.PostDirectService;
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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;
import java.util.Random;

import static java.io.File.separator;
import static net.henryco.hblog.configurations.WebConfiguration.REL_FILE_PATH;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Henry on 03/07/17.
 */
@Controller
@RequestMapping("/account/profile")
public class UserPostsController {

	private static final String UPLOAD_PATH = REL_FILE_PATH + separator + "res" +separator + "public" + separator;

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
		return "addpost";
	}


	@RequestMapping(value = "/addpost/submit/post", method = POST)
	public String submitPost(@Valid @ModelAttribute("post_form") PostForm postForm,
							 BindingResult bindingResult,
							 Principal principal) {

		if (bindingResult.hasErrors()) return "addpost";

		String iconLink = saveMultiPartFile(postForm.getFile());
		if (iconLink == null) {
			bindingResult.addError(new ObjectError("img_prev", "Image preview cannot be empty"));
			return "addpost";
		}
		System.out.println(iconLink);

		StandardPostPreview post = new StandardPostPreview();
		post.setAuthor(principal.getName());
		post.setImgLink(iconLink);
		post.setTitle(postForm.getTitle());
		post.setUpdateTime(DateTime.now(DateTimeZone.UTC).toDate());
		post.setPreviewShort(postForm.getPreviewShort());
		post.setPreviewLong(postForm.getPreviewLong());

		StandardPostContent content = new StandardPostContent(postDirectService.addPostPreview(post).getId());
		content.setContent(postForm.getContent());
		content.setAttached("TODO");
		postDirectService.addPostContent(content);

		return "redirect:/account/profile";
	}


	private static String saveMultiPartFile(MultipartFile file) {
		try {
			if (file.isEmpty()) return null;
			final int i = file.getOriginalFilename().lastIndexOf(".");
			final String name = new Random().nextLong() + System.currentTimeMillis() + ( i != -1 ? file.getOriginalFilename().substring(i) : "");
			Files.write(Paths.get(UPLOAD_PATH + name), file.getBytes());
			return name;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
