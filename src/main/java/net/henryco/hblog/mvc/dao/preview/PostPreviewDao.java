package net.henryco.hblog.mvc.dao.preview;

import net.henryco.hblog.mvc.model.post.StandardPostPreview;

import java.util.List;

/**
 * @author Henry on 15/06/17.
 */
public interface PostPreviewDao {

	StandardPostPreview getPostById(long id);
	List<StandardPostPreview> getLastPosts(long numb);
	List<StandardPostPreview> getPostsInRange(long from, long numb);

	boolean removePostPreviewById(long id);
	StandardPostPreview addPostPreview(StandardPostPreview post);
	boolean isPostExists(long id);

	long getPostsCount();

	StandardPostPreview getNewestPost();
	StandardPostPreview getOldestPost();
	StandardPostPreview getPostOlderThen(long id);
	StandardPostPreview getPostYoungerThen(long id);
}
