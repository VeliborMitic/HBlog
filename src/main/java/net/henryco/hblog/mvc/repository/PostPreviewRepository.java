package net.henryco.hblog.mvc.repository;

import net.henryco.hblog.mvc.model.dto.post.StandardPostPreview;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Temporal;

import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

/**
 * @author Henry on 15/06/17.
 */
public interface PostPreviewRepository extends JpaRepository<StandardPostPreview, Long> {


	List<StandardPostPreview> findDistinctByUpdateTimeBeforeOrderByUpdateTimeDesc(
			@Temporal(TemporalType.TIMESTAMP) Date updateTime,
			Pageable pageable
	);

	StandardPostPreview findDistinctFirstByUpdateTimeBeforeOrderByUpdateTimeDesc(
			@Temporal(TemporalType.TIMESTAMP) Date updateTime
	);

	StandardPostPreview findDistinctFirstByUpdateTimeBeforeOrderByUpdateTimeAsc(
			@Temporal(TemporalType.TIMESTAMP) Date updateTime
	);

	StandardPostPreview findFirstByUpdateTimeBeforeOrderByUpdateTimeDesc(
			@Temporal(TemporalType.TIMESTAMP) Date updateTime
	);

	StandardPostPreview findFirstByUpdateTimeAfter(
			@Temporal(TemporalType.TIMESTAMP) Date updateTime
	);

}
