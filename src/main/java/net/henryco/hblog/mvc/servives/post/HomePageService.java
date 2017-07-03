package net.henryco.hblog.mvc.servives.post;

import net.henryco.hblog.mvc.model.dao.post.preview.PostPreviewDao;
import net.henryco.hblog.mvc.model.entity.post.StandardPostPreview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Henry on 16/06/17.
 */
@Service
public class HomePageService {

	private final PostPreviewDao postPreviewDao;

	@Autowired
	public HomePageService(@Qualifier("testMockDao") PostPreviewDao postPreviewDao) {
		this.postPreviewDao = postPreviewDao;
	}



	public List<StandardPostPreview> getLastPosts(int numb) {
		return postPreviewDao.getLastPosts(numb);
	}

	public StandardPostPreview getPostPreviewById(long id) {
		return postPreviewDao.getPostById(id);
	}

	public StandardPostPreview getNewestPost() {
		return postPreviewDao.getNewestPost();
	}

	public StandardPostPreview getOldestPost() {
		return postPreviewDao.getOldestPost();
	}

}
