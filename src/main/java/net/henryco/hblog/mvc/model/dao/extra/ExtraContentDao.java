package net.henryco.hblog.mvc.model.dao.extra;

import net.henryco.hblog.mvc.model.entity.extra.PinnedBanner;
import net.henryco.hblog.mvc.model.entity.extra.PinnedNews;

import java.util.List;

/**
 * @author Henry on 17/06/17.
 */
public interface ExtraContentDao {

	PinnedNews getPinnedNewsById(long id);

	PinnedBanner getPinnedBannersById(long id);

	void removePinnedNewsById(long id);

	void removePinnedBannersById(long id);

	PinnedNews addPinnedNews(PinnedNews news);

	PinnedBanner addPinnedBanners(PinnedBanner banner);

	boolean isBannerExists(long id);

	boolean isNewsExists(long id);

	List<PinnedBanner> getActualBanners(int numb);

	List<PinnedNews> getActualNews(int numb);

	List<PinnedBanner> getIrrelevantBanners(int numb);

	List<PinnedNews> getIrrelevantNews(int numb);


}
