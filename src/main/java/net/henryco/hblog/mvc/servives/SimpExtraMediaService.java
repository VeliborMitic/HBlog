package net.henryco.hblog.mvc.servives;

import net.henryco.hblog.mvc.dao.extra.ExtraMediaDao;
import net.henryco.hblog.mvc.model.promo.PinnedBanners;
import net.henryco.hblog.mvc.model.promo.PinnedNews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Henry on 17/06/17.
 */
@Service
public class SimpExtraMediaService {

	private final ExtraMediaDao mediaDao;

	@Autowired
	public SimpExtraMediaService(ExtraMediaDao mediaDao) {
		this.mediaDao = mediaDao;
	}


	public List<PinnedBanners> getActualBanners(int numb) {
		return mediaDao.getActualBanners(numb);
	}

	public List<PinnedNews> getActualNews(int numb) {
		return mediaDao.getActualNews(numb);
	}

}
