package net.henryco.hblog.mvc.controllers.acc.user;

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
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Henry on 06/07/17.
 */
@Controller
@RequestMapping("/account/profile/addpost/submit/post")
public class SubmitPostController {


	private final ExtendedProfileService profileService;
	private final PostDirectService postDirectService;


	@Autowired
	public SubmitPostController(ExtendedProfileService profileService,
								PostDirectService postDirectService) {
		this.profileService = profileService;
		this.postDirectService = postDirectService;
	}


	@RequestMapping(method = POST)
	public String submitPost(@Valid @ModelAttribute("post_form") PostForm postForm,
							 BindingResult bindingResult,
							 Principal principal) {

		if (bindingResult.hasErrors()) return "addpost";
		final String userName = principal.getName();

		List<String> attached = new ArrayList<>();
		Long id = postForm.getId();
		StandardPostPreview post = id != null ? postDirectService.getPostPreviewById(id) : new StandardPostPreview();


		if (id != null && !post.getAuthor().equals(principal.getName())) {
			BaseUserProfile userProfile = profileService.getBaseProfileByNameOrEmail(principal.getName());
			AuthUserProfile authProfile = profileService.getAuthProfile(userProfile.getId());
			if (checkForRedirect(authProfile))
				return "redirect:/account/profile";
		}

		String iconLink = saveMultiPartFileWithNewName(postForm.getFile(), userName, UPLOAD_PATH);
		if (iconLink == null) {
			if (id == null) {
				bindingResult.addError(new ObjectError("img_prev", "Image preview cannot be empty"));
				return "addpost";
			}
			iconLink = post.getImgLink();
		}

		post = loadPostPreview(post, principal, postForm, iconLink, id);
		String postContent = loadContent(postForm, attached, userName);
		Long newId = postDirectService.addPostPreview(post).getId();
		StandardPostContent content = loadStandardPostContent(id, newId, attached, postContent);
		postDirectService.addPostContent(content);

		return "redirect:/account/profile";
	}



	private StandardPostPreview loadPostPreview(StandardPostPreview post, Principal principal,
												PostForm postForm, String iconLink, Long id) {

		post.setImgLink(iconLink);
		post.setTitle(postForm.getTitle());
		post.setPreviewShort(postForm.getPreviewShort());
		post.setPreviewLong(postForm.getPreviewLong());
		post.setAuthor(id == null ? principal.getName() : post.getAuthor());
		post.setUpdateTime(id == null ? DateTime.now(DateTimeZone.UTC).toDate() : post.getUpdateTime());
		return post;
	}


	private StandardPostContent loadStandardPostContent(Long id, Long newId,
														List<String> attached, String postContent) {
		StandardPostContent content = id == null
				? new StandardPostContent(newId)
				: postDirectService.getPostContentById(id);

		if (id != null && content.getAttached() != null)
			attached.addAll(Arrays.asList(Utils.stringToArray(content.getAttached())));
		content.setContent(postContent);
		content.setAttached(Utils.arrayToString(attached.toArray(new String[0])));
		return content;
	}


	private String loadContent(PostForm postForm, List<String> attached, String userName) {

		String postContent = postForm.getContent();
		List<MultipartFile> files = postForm.getAttachedFiles();
		for (int i = 0; i < files.size(); i++) {
			String fileName = saveMultiPartFileWithNewName(files.get(i), userName, UPLOAD_PATH);
			if (fileName != null) {
				attached.add(fileName);
				postContent = postContent.replace(getResN(i), DEF_PATH + fileName);
			}
		}
		return postContent;
	}


	private static boolean checkForRedirect(AuthUserProfile profile) {
		boolean redirect = true;
		for (String auth: profile.getAuthoritiesArray()) {
			if (auth.equals("ROLE_ADMIN")) {
				redirect = false;
				break;
			}
		}
		return redirect;
	}


	private static String getResN(int numb) {
		return "{res::[" + numb + "]}";
	}
}
