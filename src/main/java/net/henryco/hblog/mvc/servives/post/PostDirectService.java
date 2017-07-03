package net.henryco.hblog.mvc.servives.post;

import net.henryco.hblog.mvc.model.dao.post.content.PostContentDao;
import net.henryco.hblog.mvc.model.dao.post.preview.PostPreviewDao;
import net.henryco.hblog.mvc.model.entity.post.StandardPostContent;
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
	private final PostContentDao postContentDao;

	@Autowired
	public PostDirectService(@Qualifier("testMockDao") PostPreviewDao postPreviewDao,
							 PostContentDao postContentDao) {
		this.postPreviewDao = postPreviewDao;
		this.postContentDao = postContentDao;
	}

	public StandardPostPreview getPostPreviewById(long id) {
		return postPreviewDao.getPostById(id);
	}
	public StandardPostContent getPostContentById(long id) {
		return postContentDao.getPostContentById(id);
	}

	public void removePostById(long id) {
		postPreviewDao.removePostPreviewById(id);
		postContentDao.removePostContentById(id);
	}

	public StandardPostPreview addPostPreview(StandardPostPreview post) {
		return postPreviewDao.addPostPreview(post);
	}
	public StandardPostContent addPostContent(StandardPostContent content) {
		return postContentDao.addPostContent(content);
	}

	public boolean isPostExists(long id) {
		return postPreviewDao.isPostExists(id);
	}

	public List<StandardPostPreview> getAllPostsFromAuthor(String name) {
		return postPreviewDao.getPostsByAuthor(name);
	}

}
