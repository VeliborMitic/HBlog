package net.henryco.hblog.mvc.servives;

import net.henryco.hblog.mvc.dao.content.PostContentDao;
import net.henryco.hblog.mvc.dao.preview.PostPreviewDao;
import net.henryco.hblog.mvc.model.StandardPostPreview;
import net.henryco.hblog.utils.Utils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
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


	public String getPostContent(long id) {
		return contentDao.getPostContentById(id).getContent();
	}

	public String getPostTitle(long id) {
		return previewDao.getPostById(id).getTitle();
	}

	public String getPostPrevImgLink(long id) {
		return previewDao.getPostById(id).getImgLink();
	}

	public Date getPostUpdateTime(long id) {
		return previewDao.getPostById(id).getUpdateTime();
	}

	public DateTime getPostUpdateDateTime(long id) {
		return new DateTime(getPostUpdateTime(id));
	}

	public String[] getAttachedFiles(long id) {
		return Utils.stringToArray(contentDao.getPostContentById(id).getAttached());
	}

	public List<StandardPostPreview> getLastPostPreviews(long numb) {
		return previewDao.getLastPosts(numb);
	}
}
