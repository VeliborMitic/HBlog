package net.henryco.hblog.mvc.dao;

import net.henryco.hblog.mvc.repository.PostFormRepository;
import net.henryco.hblog.mvc.model.StandardPost;
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
public class PostFormDaoImp implements PostFormDao {


	private PostFormRepository postFormRepository;

	@Autowired
	public PostFormDaoImp(PostFormRepository postFormRepository) {
		this.postFormRepository = postFormRepository;
	}



	@Override
	public StandardPost getPostById(long id) {
		return postFormRepository.getOne(id);
	}

	@Override
	public StandardPost addPost(StandardPost post) {
		return postFormRepository.saveAndFlush(post);
	}

	@Override
	public boolean isPostExists(long id) {
		return postFormRepository.exists(id);
	}


	@Override
	public StandardPost getNewestPost() {
		return postFormRepository.findDistinctFirstByUpdateTimeBeforeOrderByUpdateTimeDesc(getActualDateTime());
	}

	@Override
	public StandardPost getOldestPost() {
		return postFormRepository.findDistinctFirstByUpdateTimeBeforeOrderByUpdateTimeAsc(getActualDateTime());
	}

	@Override
	public List<StandardPost> getLastPosts(long numb) {
		final Pageable limit = new PageRequest(0, (int) numb);
		return postFormRepository.findDistinctByUpdateTimeBeforeOrderByUpdateTimeDesc(getActualDateTime(), limit);
	}



	@Override
	public boolean removePostById(long id) {
		try {
			postFormRepository.delete(id);
		} catch (Exception e) {return false;}
		return true;
	}



	private static Date getActualDateTime() {
		return DateTime.now(DateTimeZone.UTC).toDate();
	}
}
