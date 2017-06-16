package net.henryco.hblog.mvc.dao.content;

import net.henryco.hblog.mvc.model.StandardPostContent;

/**
 * @author Henry on 16/06/17.
 */
public interface PostContentDao {

	StandardPostContent addPostContent(StandardPostContent content);
	void removePostContentById(Long id);
	boolean isPostContentExists(long id);
}
