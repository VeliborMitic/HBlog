package net.henryco.hblog.mvc.dao.extra;

import net.henryco.hblog.mvc.model.extra.PinnedBanners;
import net.henryco.hblog.mvc.model.extra.PinnedNews;

import java.util.List;

/**
 * @author Henry on 17/06/17.
 */
public interface ExtraContentDao {

	PinnedNews getPinnedNewsById(long id);

	PinnedBanners getPinnedBannersById(long id);

	void removePinnedNewsById(long id);

	void removePinnedBannersById(long id);

	PinnedNews addPinnedNews(PinnedNews news);

	PinnedBanners addPinnedBanners(PinnedBanners banner);

	boolean isBannerExists(long id);

	boolean isNewsExists(long id);

	List<PinnedBanners> getActualBanners(int numb);

	List<PinnedNews> getActualNews(int numb);

	List<PinnedBanners> getIrrelevantBanners(int numb);

	List<PinnedNews> getIrrelevantNews(int numb);


}
