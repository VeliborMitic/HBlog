package net.henryco.hblog.configurations;

import net.henryco.hblog.mvc.dao.PostFormDao;
import net.henryco.hblog.mvc.dao.imp.PostFormDaoImp;
import net.henryco.hblog.mvc.model.StandardPost;
import net.henryco.hblog.mvc.repository.PostFormRepository;
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



	@Bean
	public PostFormDao mockDao(PostFormRepository repository) {

		PostFormDaoImp postFormDaoImp = new PostFormDaoImp(repository);
		for (int i = 0; i < 10; i++) {
			StandardPost post = new StandardPost();
			post.setTitle("Tittle"+i);
			post.setContent(LOREM_IPSUM);
			post.setPreview(LOREM_IPSUM.substring(0, 185));
			postFormDaoImp.addPost(post);
		}
		return postFormDaoImp;
	}
}
