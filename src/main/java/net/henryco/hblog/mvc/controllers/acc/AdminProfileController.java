package net.henryco.hblog.mvc.controllers.acc;

import net.henryco.hblog.mvc.controllers.acc.form.MultiFileForm;
import net.henryco.hblog.mvc.controllers.acc.form.PostForm;
import net.henryco.hblog.mvc.controllers.acc.form.ProfileForm;
import net.henryco.hblog.mvc.model.entity.account.AuthUserProfile;
import net.henryco.hblog.mvc.model.entity.post.StandardPostContent;
import net.henryco.hblog.mvc.model.entity.post.StandardPostPreview;
import net.henryco.hblog.mvc.servives.account.ExtendedProfileService;
import net.henryco.hblog.mvc.servives.post.PostDirectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Henry on 19/06/17.
 */
@Controller
@RequestMapping("/account/admin")
public class AdminProfileController {


	private final PostDirectService postDirectService;
	private final ExtendedProfileService profileService;

	@Autowired
	public AdminProfileController(PostDirectService postDirectService, ExtendedProfileService profileService) {
		this.postDirectService = postDirectService;
		this.profileService = profileService;
	}


	@RequestMapping(method = GET)
	public String mainPanel() {
		return "admin";
	}


	@RequestMapping(value = "/posts", method = GET)
	public String managePosts(Model model) {
		model.addAttribute("posts", postDirectService.getAllPosts());
		return "posts";
	}


	@RequestMapping("/posts/delete/{id}")
	public String deletePost(@PathVariable("id") long id) {
		postDirectService.removePostById(id);
		return "redirect:/account/admin/posts";
	}


	@RequestMapping(value = "/posts/edit/{id}", method = GET)
	public String editPost(@PathVariable("id") long id, Model model) {

		StandardPostContent postContent = postDirectService.getPostContentById(id);
		StandardPostPreview postPreview = postDirectService.getPostPreviewById(id);

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


	@RequestMapping(value = "/profiles", method = GET)
	public String manageProfiles(Model model) {

		List<ProfileForm> formList = new LinkedList<>();
		profileService.getBaseProfiles().forEach(baseUserProfile -> {
			ProfileForm form = new ProfileForm();
			form.setId(baseUserProfile.getId());
			form.setUsername(baseUserProfile.getUserName());
			form.setPosition(baseUserProfile.getPosition());
			form.setEmail(baseUserProfile.getEmail());
			form.setName(baseUserProfile.getFirstName() + " " + baseUserProfile.getLastName());
			AuthUserProfile authProfile = profileService.getAuthProfile(baseUserProfile.getId());
			form.setRoles(authProfile.getAuthorities());
			form.setEnabled(authProfile.isEnabled());
			formList.add(form);
		});

		model.addAttribute("profiles", formList);
		return "profiles";
	}


	@RequestMapping(value = "/profiles/stat/switch/{id}", method = POST)
	public String switchStat(@PathVariable("id") long id, Authentication authentication) {
		if (authentication.isAuthenticated() &&
				authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			AuthUserProfile authProfile = profileService.getAuthProfile(id);
			authProfile.setEnabled(!authProfile.isEnabled());
			profileService.updateAuthUserProfile(authProfile);
		}
		return "redirect:/account/admin/profiles";
	}


	@RequestMapping(value = "/profiles/stat/delete/{id}", method = POST)
	public String deleteProfile(@PathVariable("id") long id, Authentication authentication) {
		if (authentication.isAuthenticated() &&
				authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")))
			profileService.deleteProfile(id);
		return "redirect:/account/admin/profiles";
	}
}
