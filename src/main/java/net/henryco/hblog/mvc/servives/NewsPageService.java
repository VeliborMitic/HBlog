package net.henryco.hblog.mvc.servives;

import net.henryco.hblog.mvc.dao.PostFormDao;
import net.henryco.hblog.mvc.model.StandardPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Henry on 16/06/17.
 */
@Service
public class NewsPageService {

	private final PostFormDao postFormDao;

	@Autowired
	public NewsPageService(@Qualifier("mockDao") PostFormDao postFormDao) {
		this.postFormDao = postFormDao;
	}


	public List<StandardPost> getLastPostsInRange(long page, long numb) {
		return postFormDao.getPostsInRange(page, numb);
	}

	public long getNewsCount() {
		return postFormDao.getPostsCount();
	}
}
