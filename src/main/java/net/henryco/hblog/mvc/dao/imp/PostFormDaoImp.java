package net.henryco.hblog.mvc.dao.imp;

import net.henryco.hblog.mvc.dao.PostFormDao;
import net.henryco.hblog.mvc.repository.PostFormRepository;
import net.henryco.hblog.mvc.projs.StandardPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Henry on 15/06/17.
 */
//@Repository
public class PostFormDaoImp //implements PostFormDao
{

	private PostFormRepository postFormRepository;
/*
	@Autowired
	public PostFormDaoImp(PostFormRepository postFormRepository) {
		this.postFormRepository = postFormRepository;
	}

	@Override
	public StandardPost getPostById(long id) {
		return postFormRepository.getOne(id);
	}

	@Override
	public List<StandardPost> getLastPosts(long numb) {

		return null;
	}

	@Override
	public boolean removePostById(long id) {
		try {
			postFormRepository.delete(id);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean addPost(StandardPost post) {
		try {
			postFormRepository.saveAndFlush(post);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isPostExists(long id) {
		return postFormRepository.exists(id);
	}
	*/
}
