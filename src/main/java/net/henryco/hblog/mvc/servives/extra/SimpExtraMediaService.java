package net.henryco.hblog.mvc.servives.extra;

import net.henryco.hblog.mvc.model.dao.extra.ExtraContentDao;
import net.henryco.hblog.mvc.model.entity.extra.PinnedBanners;
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


	public List<PinnedBanners> getActualBanners(int numb) {
		return mediaDao.getActualBanners(numb);
	}

	public List<PinnedNews> getActualNews(int numb) {
		return mediaDao.getActualNews(numb);
	}

}
