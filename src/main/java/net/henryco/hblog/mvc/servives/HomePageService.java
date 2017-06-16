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
public class HomePageService {

	private final PostFormDao postFormDao;

	@Autowired
	public HomePageService(@Qualifier("testMockDao") PostFormDao postFormDao) {
		this.postFormDao = postFormDao;
	}



	public List<StandardPost> getLastPosts(int numb) {
		return postFormDao.getLastPosts(numb);
	}


	public StandardPost getNewestPost() {
		return postFormDao.getNewestPost();
	}

	public StandardPost getOldestPost() {
		return postFormDao.getOldestPost();
	}

}
