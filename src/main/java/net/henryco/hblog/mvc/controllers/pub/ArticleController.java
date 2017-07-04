package net.henryco.hblog.mvc.controllers.pub;

import net.henryco.hblog.mvc.model.entity.account.BaseUserProfile;
import net.henryco.hblog.mvc.model.entity.post.StandardPostContent;
import net.henryco.hblog.mvc.model.entity.post.StandardPostPreview;
import net.henryco.hblog.mvc.model.entity.extra.PinnedBanner;
import net.henryco.hblog.mvc.servives.account.BaseProfileService;
import net.henryco.hblog.mvc.servives.post.ArticlePageService;
import net.henryco.hblog.mvc.servives.extra.SimpExtraMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Henry on 17/06/17.
 */
@Controller
@RequestMapping("/news/article")
public class ArticleController {

	private final ArticlePageService articlePageService;
	private final SimpExtraMediaService mediaService;
	private final SimpleDateFormat dateFormat;
	private final BaseProfileService profileService;

	@Autowired
	public ArticleController(ArticlePageService articlePageService,
							 SimpExtraMediaService mediaService,
							 BaseProfileService profileService) {
		this.articlePageService = articlePageService;
		this.profileService = profileService;
		this.mediaService = mediaService;
		this.dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
	}


	@RequestMapping("/")
	public String news() {
		return "redirect:/news/";
	}

	@RequestMapping(value = "/{id}", method = GET)
	public String article(@PathVariable("id") long id, Model model) {

		List<PinnedBanner> pinnedBanners = mediaService.getActualBanners(1);
		if (pinnedBanners != null)
			model.addAttribute("banners", pinnedBanners);

		List<StandardPostPreview> previews = articlePageService.getLastPostPreviews(4);
		if (previews != null && !previews.isEmpty()) {
			model.addAttribute("last_preview", previews.remove(previews.size() - 1));
			model.addAttribute("post_previews", previews);
		}

		StandardPostPreview preview = articlePageService.getPostPreviewById(id);
		StandardPostContent content = articlePageService.getPostContentById(id);

		String authorUserName = preview.getAuthor();
		String previewName = "";
		if (authorUserName != null) {
			BaseUserProfile profile = profileService.getUserProfileByUserName(authorUserName);
			if (profile != null) {
				previewName = " by "+profile.getFirstName();
				if (profile.getLastName() != null) previewName += (" " + profile.getLastName());
			}
		}
		model.addAttribute("post_update_time", dateFormat.format(preview.getUpdateTime()) + " " + previewName);
		model.addAttribute("post_content", content.getContent());
		model.addAttribute("post_title", preview.getTitle());
		model.addAttribute("article_id", id);
		model.addAttribute("article_prev", articlePageService.getPostYoungerThen(id));
		model.addAttribute("article_next", articlePageService.getPostOlderThen(id));

		return "article";
	}

}
