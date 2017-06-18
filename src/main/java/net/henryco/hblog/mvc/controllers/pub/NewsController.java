package net.henryco.hblog.mvc.controllers.pub;

import net.henryco.hblog.mvc.model.post.StandardPostPreview;
import net.henryco.hblog.mvc.model.extra.PinnedBanners;
import net.henryco.hblog.mvc.servives.post.NewsPageService;
import net.henryco.hblog.mvc.servives.extra.SimpExtraMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Henry on 15/06/17.
 */
@Controller
@RequestMapping("/news")
public class NewsController {

	private static final long NEWS_ON_PAGE = 6L;
	private static final int LINK_PAGE_NUMB = 7;

	private final NewsPageService newsPageService;
	private final SimpExtraMediaService mediaService;

	@Autowired
	public NewsController(NewsPageService newsPageService,
						  SimpExtraMediaService mediaService) {
		this.newsPageService = newsPageService;
		this.mediaService = mediaService;
	}


	@RequestMapping(method = GET)
	public String news() {
		return "redirect:/news/1";
	}



	@RequestMapping(value = "/{numb}", method = GET)
	public String newsPage(@PathVariable("numb") long pageNumb, Model model) {

		long newsCount = newsPageService.getNewsCount();
		long maxPages = (long) Math.ceil((double)newsCount / (double)NEWS_ON_PAGE);

		if (pageNumb > maxPages) return "redirect:/news/"+maxPages;
		if (pageNumb <= 0) return news();
		if (pageNumb > 1) model.addAttribute("pageNumber", pageNumb);

		List<PinnedBanners> pinnedBanners = mediaService.getActualBanners(1);
		if (pinnedBanners != null)
			model.addAttribute("banners", pinnedBanners);

		List<Long> linksArray = getLinksArray(LINK_PAGE_NUMB, pageNumb, maxPages);
		List<StandardPostPreview> posts = newsPageService.getLastPostsInRange(pageNumb - 1, NEWS_ON_PAGE);
		model.addAttribute("posts", posts);
		model.addAttribute("page_prev", pageNumb - 1);
		model.addAttribute("page_next", pageNumb + 1);
		model.addAttribute("page_max", maxPages);
		model.addAttribute("link_array", linksArray);
		if (!linksArray.isEmpty()) {
			model.addAttribute("link_page_min", linksArray.get(0));
			model.addAttribute("link_page_max", linksArray.get(linksArray.size() - 1));
		} else {
			model.addAttribute("link_page_min", 1);
			model.addAttribute("link_page_max", 1);
		}

		return "news";
	}



	private static List<Long> getLinksArray(int size, long page, long max) {

		List<Long> list = new ArrayList<>();
		if (size > max) {
			for (long i = 1; i < max; i++) list.add(i);
			return list;
		}

		long start = page - ((size - 1) / 2);
		if (start <= 0) {
			for (long i = 1; i < size; i++) list.add(i);
			return list;
		}

		long end = start + size;
		if (end > max) {
			for (long i = max - size; i <= max; i++) list.add(i);
			return list;
		}

		for (int i = 0; i < size; i++) {
			long p = start + i;
			if (p >= 1 && p < max) list.add(p);
		}

		return list;
	}

}
