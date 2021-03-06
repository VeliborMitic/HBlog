package net.henryco.hblog.mvc.model.dao.post.uni;


import net.henryco.hblog.mvc.model.entity.post.StandardPostContent;
import net.henryco.hblog.mvc.model.entity.post.StandardPostPreview;

/**
 * @author Henry on 16/06/17.
 */
public interface PostUniDao {

	void addPost(StandardPostPreview postPreview, StandardPostContent content);
	void addPost(StandardPostPreview postPreview, String content);
	void deletePost(long id);
	boolean isPostExists(long id);
}
