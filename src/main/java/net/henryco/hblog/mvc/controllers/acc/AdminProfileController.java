package net.henryco.hblog.mvc.controllers.acc;

import net.henryco.hblog.mvc.controllers.acc.form.MultiFileForm;
import net.henryco.hblog.mvc.controllers.acc.form.PostForm;
import net.henryco.hblog.mvc.controllers.acc.form.ProfileForm;
import net.henryco.hblog.mvc.model.entity.account.AuthUserProfile;
import net.henryco.hblog.mvc.model.entity.extra.PinnedNews;
import net.henryco.hblog.mvc.model.entity.post.StandardPostContent;
import net.henryco.hblog.mvc.model.entity.post.StandardPostPreview;
import net.henryco.hblog.mvc.servives.account.ExtendedProfileService;
import net.henryco.hblog.mvc.servives.extra.SimpExtraMediaService;
import net.henryco.hblog.mvc.servives.post.PostDirectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.io.File;
import java.util.LinkedList;
import java.util.List;

import static net.henryco.hblog.configurations.WebConfiguration.AVATAR_UPLOAD_DIR;
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
	private final SimpExtraMediaService mediaService;

	@Autowired
	public AdminProfileController(PostDirectService postDirectService,
								  ExtendedProfileService profileService,
								  SimpExtraMediaService mediaService) {
		this.postDirectService = postDirectService;
		this.profileService = profileService;
		this.mediaService = mediaService;
	}



	@RequestMapping(method = GET)
	public String mainPanel() {
		return "admin";
	}



	@RequestMapping(value = "/posts", method = GET)
	public String managePosts(Model model) {

		StringBuilder actual = new StringBuilder();
		for (PinnedNews news: mediaService.getActualNews())
			actual.append(news.getId()).append(", ");
		if (actual.length() >= 2) actual.delete(actual.length() - 2, actual.length());

		model.addAttribute("actual_news", actual.toString());
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



	@RequestMapping(value = "/posts/actual", method = POST)
	public String setActual(@RequestParam("actual") String actual, Authentication authentication) {

		if (authentication.isAuthenticated() &&
				authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) try {

			for (PinnedNews news: mediaService.getActualNews()) {
				news.setActual(false);
				mediaService.saveNews(news);
			}

			if (actual != null && !actual.isEmpty()) {
				String[] numb = actual.split(",");
				for (int i = 0; i < numb.length; i++) {
					numb[i] = numb[i].trim();
					long n = Long.valueOf(numb[i]);

					PinnedNews pinnedNews = mediaService.isActualNewsExists(n)
							? mediaService.getNewsById(n)
							: new PinnedNews(n);

					pinnedNews.setActual(true);
					mediaService.saveNews(pinnedNews);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/account/admin/posts";
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
				authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			try {
				new File(AVATAR_UPLOAD_DIR + profileService.getBaseProfile(id).getIconLink()).deleteOnExit();
			} catch (Exception ignored) {}
			profileService.deleteProfile(id);
		}
		return "redirect:/account/admin/profiles";
	}

}
