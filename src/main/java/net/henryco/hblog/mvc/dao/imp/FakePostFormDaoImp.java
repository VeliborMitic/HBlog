package net.henryco.hblog.mvc.dao.imp;

import net.henryco.hblog.mvc.dao.PostFormDao;
import net.henryco.hblog.mvc.projs.StandardPost;
import net.henryco.hblog.mvc.repository.FakePostFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Henry on 15/06/17.
 */
@Repository
public class FakePostFormDaoImp implements PostFormDao {

	private String loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut " +
			"labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat." +
			" Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur." +
			" Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

	private FakePostFormRepository repository;

	@Autowired
	public FakePostFormDaoImp(FakePostFormRepository repository) {
		this.repository = repository;
		for (int i = 0; i < 5; i++)
			addPost(new StandardPost(i, "Tittle"+i, loremIpsum));
	}

	@Override
	public StandardPost getPostById(long id) {
		return repository.getOne(id);
	}

	@Override
	public List<StandardPost> getLastPosts(long numb) {
		List<StandardPost> list = new ArrayList<>();
		for (long i = numb; i > 0; i--)
			list.add(getPostById(i));
		return list;
	}

	@Override
	public boolean removePostById(long id) {
		repository.remove(id);
		return true;
	}

	@Override
	public boolean addPost(StandardPost post) {
		repository.save(post);
		return true;
	}

	@Override
	public boolean isPostExists(long id) {
		return repository.exists(id);
	}

}
