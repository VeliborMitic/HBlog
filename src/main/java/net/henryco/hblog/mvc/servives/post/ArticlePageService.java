package net.henryco.hblog.mvc.servives.post;

import net.henryco.hblog.mvc.model.dao.post.content.PostContentDao;
import net.henryco.hblog.mvc.model.dao.post.preview.PostPreviewDao;
import net.henryco.hblog.mvc.model.dto.post.StandardPostContent;
import net.henryco.hblog.mvc.model.dto.post.StandardPostPreview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Henry on 17/06/17.
 */
@Service
public class ArticlePageService {

	private final PostPreviewDao previewDao;
	private final PostContentDao contentDao;

	@Autowired
	public ArticlePageService(@Qualifier("testMockDao") PostPreviewDao previewDao,
							  PostContentDao contentDao) {
		this.previewDao = previewDao;
		this.contentDao = contentDao;
	}


	public StandardPostContent getPostContentById(long id) {
		return contentDao.getPostContentById(id);
	}

	public StandardPostPreview getPostPreviewById(long id) {
		return previewDao.getPostById(id);
	}

	public long getPostOlderThen(long id) {
		StandardPostPreview preview = previewDao.getPostOlderThen(id);
		return preview == null ? id : preview.getId();
	}

	public long getPostYoungerThen(long id) {
		StandardPostPreview preview = previewDao.getPostYoungerThen(id);
		return preview == null ? id : preview.getId();
	}

	public List<StandardPostPreview> getLastPostPreviews(long numb) {
		return previewDao.getLastPosts(numb);
	}
}
