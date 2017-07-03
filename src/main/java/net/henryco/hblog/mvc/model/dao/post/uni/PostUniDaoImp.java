package net.henryco.hblog.mvc.model.dao.post.uni;

import net.henryco.hblog.mvc.model.dto.post.StandardPostContent;
import net.henryco.hblog.mvc.model.dto.post.StandardPostPreview;
import net.henryco.hblog.mvc.repository.PostContentRepository;
import net.henryco.hblog.mvc.repository.PostPreviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Henry on 16/06/17.
 */
@Repository
public class PostUniDaoImp implements PostUniDao {


	private final PostContentRepository contentRepository;
	private final PostPreviewRepository previewRepository;

	@Autowired
	public PostUniDaoImp(PostContentRepository contentRepository, PostPreviewRepository previewRepository) {
		this.contentRepository = contentRepository;
		this.previewRepository = previewRepository;
	}

	@Override
	public void addPost(StandardPostPreview postPreview, StandardPostContent content) {
		StandardPostPreview saved = previewRepository.saveAndFlush(postPreview);
		content.setId(saved.getId());
		contentRepository.saveAndFlush(content);
	}

	@Override
	public void addPost(StandardPostPreview postPreview, String content) {
		addPost(postPreview, new StandardPostContent(content));
	}

	@Override
	public void deletePost(long id) {
		contentRepository.delete(id);
		previewRepository.delete(id);
	}

	@Override
	public boolean isPostExists(long id) {
		return previewRepository.exists(id) && contentRepository.exists(id);
	}
}
