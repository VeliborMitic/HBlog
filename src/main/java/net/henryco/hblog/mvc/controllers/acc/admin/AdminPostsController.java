package net.henryco.hblog.mvc.controllers.acc.admin;

import net.henryco.hblog.mvc.controllers.form.MultiFileForm;
import net.henryco.hblog.mvc.controllers.form.PostForm;
import net.henryco.hblog.mvc.model.entity.extra.PinnedNews;
import net.henryco.hblog.mvc.model.entity.post.StandardPostPreview;
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

import static net.henryco.hblog.utils.Utils.loadPostForm;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Henry on 06/07/17.
 */
@Controller
@RequestMapping("/account/admin/posts")
public class AdminPostsController {

	private final PostDirectService postDirectService;
	private final SimpExtraMediaService mediaService;

	@Autowired
	public AdminPostsController(PostDirectService postDirectService, SimpExtraMediaService mediaService) {
		this.postDirectService = postDirectService;
		this.mediaService = mediaService;
	}


	@RequestMapping(method = GET)
	public String managePosts(Model model) {

		StringBuilder actual = new StringBuilder();
		for (PinnedNews news: mediaService.getActualNews())
			actual.append(news.getId()).append(", ");
		if (actual.length() >= 2) actual.delete(actual.length() - 2, actual.length());

		model.addAttribute("actual_news", actual.toString());
		model.addAttribute("posts", postDirectService.getAllPosts());
		return "posts";
	}



	@RequestMapping("/delete/{id}")
	public String deletePost(@PathVariable("id") long id) {
		postDirectService.removePostById(id);
		return "redirect:/account/admin/posts";
	}




	@RequestMapping(value = "/edit/{id}", method = GET)
	public String editPost(@PathVariable("id") long id, Model model) {

		PostForm postForm = loadPostForm(new PostForm(), postDirectService.getPostPreviewById(id));
		postForm.setContent(postDirectService.getPostContentById(id).getContent());

		model.addAttribute("post_form", postForm);
		model.addAttribute("attached_res", new MultiFileForm());
		return "addpost";
	}





	@RequestMapping(value = "/actual", method = POST)
	public String setActualNews(@RequestParam("actual") String actual, Authentication authentication) {

		if (authentication.isAuthenticated() &&
				authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) try {

			for (PinnedNews news: mediaService.getActualNews()) {
				news.setActual(false);
				mediaService.saveNews(news);
			}

			if (actual == null || actual.isEmpty())
				return "redirect:/account/admin/posts";

			actualizeNews(actual.split(","), postDirectService, mediaService);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/account/admin/posts";
	}


	private static void actualizeNews(String[] newsNumbers,
									  PostDirectService postDirectService,
									  SimpExtraMediaService mediaService) throws Exception {

		for (String n: newsNumbers) {
			long number = Long.valueOf(n.trim());

			if (postDirectService.isPostExists(number)) {
				PinnedNews pinnedNews = mediaService.isNewsExists(number)
						? mediaService.getNewsById(number)
						: new PinnedNews(number);
				pinnedNews.setActual(true);
				mediaService.saveNews(pinnedNews);
			}
		}

	}

}
