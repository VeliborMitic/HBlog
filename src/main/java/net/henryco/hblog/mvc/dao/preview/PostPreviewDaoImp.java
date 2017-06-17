package net.henryco.hblog.mvc.dao.preview;

import net.henryco.hblog.mvc.model.StandardPostPreview;
import net.henryco.hblog.mvc.repository.PostPreviewRepository;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author Henry on 15/06/17.
 */
@Repository
public class PostPreviewDaoImp implements PostPreviewDao {


	private PostPreviewRepository postPreviewRepository;

	@Autowired
	public PostPreviewDaoImp(PostPreviewRepository postPreviewRepository) {
		this.postPreviewRepository = postPreviewRepository;
	}



	@Override
	public StandardPostPreview getPostById(long id) {
		return postPreviewRepository.getOne(id);
	}

	@Override
	public StandardPostPreview addPostPreview(StandardPostPreview post) {
		return postPreviewRepository.saveAndFlush(post);
	}

	@Override
	public boolean isPostExists(long id) {
		return postPreviewRepository.exists(id);
	}

	@Override
	public long getPostsCount() {
		return postPreviewRepository.count();
	}

	@Override
	public StandardPostPreview getNewestPost() {
		return postPreviewRepository.findDistinctFirstByUpdateTimeBeforeOrderByUpdateTimeDesc(getActualDateTime());
	}

	@Override
	public StandardPostPreview getOldestPost() {
		return postPreviewRepository.findDistinctFirstByUpdateTimeBeforeOrderByUpdateTimeAsc(getActualDateTime());
	}

	@Override
	public List<StandardPostPreview> getLastPosts(long numb) {
		return getPostsInRange(0, numb);
	}

	@Override
	public List<StandardPostPreview> getPostsInRange(long from, long numb) {
		final Pageable limit = new PageRequest((int)from, (int)numb);
		return postPreviewRepository.findDistinctByUpdateTimeBeforeOrderByUpdateTimeDesc(getActualDateTime(), limit);
	}

	@Override
	public boolean removePostPreviewById(long id) {
		try {
			postPreviewRepository.delete(id);
		} catch (Exception e) {return false;}
		return true;
	}

	@Override
	public StandardPostPreview getPostOlderThen(long id) {
		return postPreviewRepository.findFirstByUpdateTimeBeforeOrderByUpdateTimeDesc(
				postPreviewRepository.getOne(id).getUpdateTime()
		);
	}

	@Override
	public StandardPostPreview getPostYoungerThen(long id) {
		return postPreviewRepository.findFirstByUpdateTimeAfter(
				postPreviewRepository.getOne(id).getUpdateTime()
		);
	}

	private static Date getActualDateTime() {
		return DateTime.now(DateTimeZone.UTC).toDate();
	}
}
