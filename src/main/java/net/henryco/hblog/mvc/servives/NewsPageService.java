package net.henryco.hblog.mvc.servives;

import net.henryco.hblog.mvc.dao.preview.PostPreviewDao;
import net.henryco.hblog.mvc.model.StandardPostPreview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Henry on 16/06/17.
 */
@Service
public class NewsPageService {

	private final PostPreviewDao postPreviewDao;

	@Autowired
	public NewsPageService(@Qualifier("testMockDao") PostPreviewDao postPreviewDao) {
		this.postPreviewDao = postPreviewDao;
	}


	public List<StandardPostPreview> getLastPostsInRange(long page, long numb) {
		return postPreviewDao.getPostsInRange(page, numb);
	}

	public long getNewsCount() {
		return postPreviewDao.getPostsCount();
	}
}
