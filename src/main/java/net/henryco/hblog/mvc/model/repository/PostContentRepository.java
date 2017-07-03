package net.henryco.hblog.mvc.model.repository;

import net.henryco.hblog.mvc.model.entity.post.StandardPostContent;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Henry on 16/06/17.
 */
public interface PostContentRepository extends JpaRepository<StandardPostContent, Long> {

}
