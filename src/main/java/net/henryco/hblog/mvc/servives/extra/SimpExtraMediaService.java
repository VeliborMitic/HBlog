package net.henryco.hblog.mvc.servives.extra;

import net.henryco.hblog.mvc.model.dao.extra.ExtraContentDao;
import net.henryco.hblog.mvc.model.entity.extra.PinnedBanner;
import net.henryco.hblog.mvc.model.entity.extra.PinnedNews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Henry on 17/06/17.
 */
@Service
public class SimpExtraMediaService {

	private final ExtraContentDao mediaDao;

	@Autowired
	public SimpExtraMediaService(ExtraContentDao mediaDao) {
		this.mediaDao = mediaDao;
	}


	public List<PinnedBanner> getActualBanners(int numb) {
		return mediaDao.getActualBanners(numb);
	}

	public List<PinnedNews> getActualNews(int numb) {
		return mediaDao.getActualNews(numb);
	}

	public List<PinnedBanner> getActualBanners() {
		return getActualBanners(Integer.MAX_VALUE);
	}

	public List<PinnedNews> getActualNews() {
		return getActualNews(Integer.MAX_VALUE);
	}

	public PinnedNews getNewsById(long id) {
		return mediaDao.getPinnedNewsById(id);
	}

	public void saveNews(PinnedNews news) {
		mediaDao.addPinnedNews(news);
	}

	public void saveBanner(PinnedBanner banner) {
		mediaDao.addPinnedBanners(banner);
	}


	public List<PinnedBanner> getAllBanners() {
		return mediaDao.getAllBanners();
	}

	public PinnedBanner getBannerById(long id) {
		return mediaDao.getPinnedBannersById(id);
	}

	public boolean isNewsExists(long id) {
		return mediaDao.isNewsExists(id);
	}

	public boolean isBannerExists(long id) {
		return mediaDao.isBannerExists(id);
	}

	public void deleteBanner(long id) {
		mediaDao.removePinnedBannersById(id);
	}

	public void deleteNews(long id) {
		mediaDao.removePinnedNewsById(id);
	}

}
