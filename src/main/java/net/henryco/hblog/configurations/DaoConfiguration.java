package net.henryco.hblog.configurations;

import net.henryco.hblog.mvc.dao.preview.PostPreviewDao;
import net.henryco.hblog.mvc.dao.preview.PostPreviewDaoImp;
import net.henryco.hblog.mvc.model.StandardPostPreview;
import net.henryco.hblog.mvc.repository.PostPreviewRepository;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Henry on 15/06/17.
 */
@Configuration
public class DaoConfiguration {

	private static final String LOREM_IPSUM = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor " +
			"incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi " +
			"ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu " +
			"fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";



	@Bean @Qualifier("testMockDao")
	public PostPreviewDao mockDao(PostPreviewRepository previewRepository) {

		PostPreviewDaoImp postFormDaoImp = new PostPreviewDaoImp(previewRepository);
		for (int i = 0; i < 76; i++) {
			StandardPostPreview post = new StandardPostPreview();
			post.setTitle("Tittle"+i);
			post.setPreviewShort(LOREM_IPSUM.substring(0, Math.min(LOREM_IPSUM.length(), 185)));
			post.setPreviewLong(LOREM_IPSUM.substring(0, Math.min(LOREM_IPSUM.length(), 330)));
			post.setUpdateTime(DateTime.now(DateTimeZone.UTC).toDate());
			postFormDaoImp.addPostPreview(post);
		}
		return postFormDaoImp;
	}
}
