package net.henryco.hblog.mvc.controllers.acc;


import net.henryco.hblog.mvc.controllers.acc.form.MultiFileForm;
import net.henryco.hblog.mvc.controllers.acc.form.PostForm;
import net.henryco.hblog.mvc.model.entity.account.AuthUserProfile;
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
import static net.henryco.hblog.utils.Utils.saveMultiPartFileWithNewName;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Henry on 03/07/17.
 */
@Controller
@RequestMapping("/account/profile")
public class UserPostsController {


	private final ExtendedProfileService profileService;
	private final PostDirectService postDirectService;

	@Autowired
	public UserPostsController(ExtendedProfileService profileService, PostDirectService postDirectService) {
		this.profileService = profileService;
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




	@RequestMapping(value = "/addpost/submit/post", method = POST)
	public String submitPost(@Valid @ModelAttribute("post_form") PostForm postForm,
							 BindingResult bindingResult,
							 Principal principal) {

		if (bindingResult.hasErrors()) return "addpost";
		final String userName = principal.getName();

		Long id = postForm.getId();
		StandardPostPreview post = id != null ? postDirectService.getPostPreviewById(id) : new StandardPostPreview();
		if (id != null && !post.getAuthor().equals(principal.getName())) {

			AuthUserProfile profile = profileService.getAuthProfile(profileService.getBaseProfileByNameOrEmail(principal.getName()).getId());
			boolean redirect = true;
			for (String auth: profile.getAuthoritiesArray()) {
				if (auth.equals("ROLE_ADMIN")) {
					redirect = false;
					break;
				}
			}
			if (redirect) return "redirect:/account/profile";
		}

		String iconLink = saveMultiPartFileWithNewName(postForm.getFile(), userName, UPLOAD_PATH);
		if (iconLink == null) {
			if (id == null) {
				bindingResult.addError(new ObjectError("img_prev", "Image preview cannot be empty"));
				return "addpost";
			}
			iconLink = post.getImgLink();
		}

		post.setImgLink(iconLink);
		post.setTitle(postForm.getTitle());
		post.setAuthor(id == null ? principal.getName() : post.getAuthor());
		post.setUpdateTime(id == null ? DateTime.now(DateTimeZone.UTC).toDate() : post.getUpdateTime());
		post.setPreviewShort(postForm.getPreviewShort());
		post.setPreviewLong(postForm.getPreviewLong());

		List<String> attached = new ArrayList<>();
		String postContent = postForm.getContent();
		int i = 0;
		for (MultipartFile file: postForm.getAttachedFiles()) {
			String fileName = saveMultiPartFileWithNewName(file, userName, UPLOAD_PATH);
			if (fileName != null) {
				attached.add(fileName);
				postContent = postContent.replace(getResN(i), DEF_PATH + fileName);
			}
			i++;
		}

		Long newId = postDirectService.addPostPreview(post).getId();
		StandardPostContent content = id == null ? new StandardPostContent(newId) : postDirectService.getPostContentById(id);
		if (id != null && content.getAttached() != null)
			attached.addAll(Arrays.asList(Utils.stringToArray(content.getAttached())));
		content.setContent(postContent);
		content.setAttached(Utils.arrayToString(attached.toArray(new String[0])));
		postDirectService.addPostContent(content);

		return "redirect:/account/profile";
	}



	@RequestMapping(value = "/editpost/{id}", method = GET)
	public String edit(@PathVariable("id") long id, Model model, Principal principal) {

		StandardPostPreview postPreview = postDirectService.getPostPreviewById(id);
		if (postPreview.getAuthor().equals(principal.getName())) {
			StandardPostContent postContent = postDirectService.getPostContentById(id);

			PostForm postForm = new PostForm();
			postForm.setId(postPreview.getId());
			postForm.setTitle(postPreview.getTitle());
			postForm.setPreviewShort(postPreview.getPreviewShort());
			postForm.setPreviewLong(postPreview.getPreviewLong());
			postForm.setContent(postContent.getContent());

			model.addAttribute("post_form", postForm);
			model.addAttribute("attached_res", new MultiFileForm());
			return "addpost";
		}

		return "redirect:/account/profile";
	}



	private static String getResN(int numb) {
		return "{res::[" + numb + "]}";
	}




}
