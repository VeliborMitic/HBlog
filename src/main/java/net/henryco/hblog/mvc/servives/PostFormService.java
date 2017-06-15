package net.henryco.hblog.mvc.servives;

import net.henryco.hblog.mvc.dao.PostFormDao;
import net.henryco.hblog.mvc.model.StandardPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Henry on 15/06/17.
 */
@Service
public class PostFormService {

	private final PostFormDao postFormDao;

	@Autowired
	public PostFormService(@Qualifier("mockDao") PostFormDao postFormDao) {
		this.postFormDao = postFormDao;
	}

	public StandardPost getPostById(long id) {
		return postFormDao.getPostById(id);
	}

	public List<StandardPost> getLastPosts(long numb) {
		return postFormDao.getLastPosts(numb);
	}

	public boolean removePostById(long id) {
		return postFormDao.removePostById(id);
	}

	public StandardPost addPost(StandardPost post) {
		return postFormDao.addPost(post);
	}

	public boolean isPostExists(long id) {
		return postFormDao.isPostExists(id);
	}
}
