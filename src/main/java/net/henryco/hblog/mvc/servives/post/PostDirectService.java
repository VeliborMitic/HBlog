package net.henryco.hblog.mvc.servives.post;

import net.henryco.hblog.mvc.model.dao.post.preview.PostPreviewDao;
import net.henryco.hblog.mvc.model.entity.post.StandardPostPreview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author Henry on 15/06/17.
 */
@Service
public class PostDirectService {

	private final PostPreviewDao postPreviewDao;

	@Autowired
	public PostDirectService(@Qualifier("testMockDao") PostPreviewDao postPreviewDao) {
		this.postPreviewDao = postPreviewDao;
	}


	public StandardPostPreview getPostById(long id) {
		return postPreviewDao.getPostById(id);
	}


	public boolean removePostById(long id) {
		return postPreviewDao.removePostPreviewById(id);
	}


	public StandardPostPreview addPost(StandardPostPreview post) {
		return postPreviewDao.addPostPreview(post);
	}


	public boolean isPostExists(long id) {
		return postPreviewDao.isPostExists(id);
	}

	public List<StandardPostPreview> getAllPostsFromAuthor(String name) {
		return postPreviewDao.getPostsByAuthor(name);
	}

}
