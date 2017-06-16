package net.henryco.hblog.mvc.dao;

import net.henryco.hblog.mvc.model.StandardPost;

import java.util.List;

/**
 * @author Henry on 15/06/17.
 */
public interface PostFormDao {

	StandardPost getPostById(long id);
	List<StandardPost> getLastPosts(long numb);
	List<StandardPost> getPostsInRange(long from, long numb);

	boolean removePostById(long id);
	StandardPost addPost(StandardPost post);
	boolean isPostExists(long id);

	long getPostsCount();

	StandardPost getNewestPost();
	StandardPost getOldestPost();
}
