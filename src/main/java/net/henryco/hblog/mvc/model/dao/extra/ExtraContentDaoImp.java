package net.henryco.hblog.mvc.model.dao.extra;

import net.henryco.hblog.mvc.model.entity.extra.PinnedBanner;
import net.henryco.hblog.mvc.model.entity.extra.PinnedNews;
import net.henryco.hblog.mvc.model.repository.PinnedBannersRepository;
import net.henryco.hblog.mvc.model.repository.PinnedNewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Henry on 17/06/17.
 */
@Repository
public class ExtraContentDaoImp implements ExtraContentDao {

	private final PinnedBannersRepository bannersRepository;
	private final PinnedNewsRepository newsRepository;

	@Autowired
	public ExtraContentDaoImp(PinnedBannersRepository bannersRepository,
							  PinnedNewsRepository newsRepository) {
		this.bannersRepository = bannersRepository;
		this.newsRepository = newsRepository;
	}

	@Override
	public PinnedNews getPinnedNewsById(long id) {
		return newsRepository.getOne(id);
	}

	@Override
	public PinnedBanner getPinnedBannersById(long id) {
		return bannersRepository.getOne(id);
	}

	@Override
	public void removePinnedNewsById(long id) {
		newsRepository.delete(id);
	}

	@Override
	public void removePinnedBannersById(long id) {
		bannersRepository.delete(id);
	}

	@Override
	public PinnedNews addPinnedNews(PinnedNews news) {
		return newsRepository.saveAndFlush(news);
	}

	@Override
	public PinnedBanner addPinnedBanners(PinnedBanner banner) {
		return bannersRepository.saveAndFlush(banner);
	}

	@Override
	public boolean isBannerExists(long id) {
		return bannersRepository.exists(id);
	}

	@Override
	public boolean isNewsExists(long id) {
		return newsRepository.exists(id);
	}

	@Override
	public List<PinnedBanner> getActualBanners(int numb) {
		return bannersRepository.findByActualIsTrueOrderByIdDesc(new PageRequest(0, numb));
	}

	@Override
	public List<PinnedNews> getActualNews(int numb) {
		return newsRepository.findByActualIsTrueOrderByIdDesc(new PageRequest(0, numb));
	}

	@Override
	public List<PinnedBanner> getIrrelevantBanners(int numb) {
		return bannersRepository.findByActualIsFalseOrderByIdDesc(new PageRequest(0, numb));
	}

	@Override
	public List<PinnedNews> getIrrelevantNews(int numb) {
		return newsRepository.findByActualIsFalseOrderByIdDesc(new PageRequest(0, numb));
	}

	@Override
	public List<PinnedBanner> getAllBanners() {
		return bannersRepository.findAll();
	}
}
