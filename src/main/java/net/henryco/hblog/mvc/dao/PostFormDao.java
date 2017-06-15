package net.henryco.hblog.mvc.dao;

import net.henryco.hblog.mvc.projs.StandardPost;

import java.util.List;

/**
 * @author Henry on 15/06/17.
 */
public interface PostFormDao {

	StandardPost getPostById(long id);
	List<StandardPost> getLastPosts(long numb);
	boolean removePostById(long id);
	boolean addPost(StandardPost post);
	boolean isPostExists(long id);

}
