package net.henryco.hblog.mvc.repository;

import net.henryco.hblog.mvc.model.StandardPost;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Temporal;

import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

/**
 * @author Henry on 15/06/17.
 */
public interface PostFormRepository extends JpaRepository<StandardPost, Long> {


	List<StandardPost> findDistinctByUpdateTimeBeforeOrderByUpdateTimeDesc(
			@Temporal(TemporalType.TIMESTAMP) Date updateTime,
			Pageable pageable
	);


	StandardPost findDistinctFirstByUpdateTimeBeforeOrderByUpdateTimeDesc(
			@Temporal(TemporalType.TIMESTAMP) Date updateTime
	);


	StandardPost findDistinctFirstByUpdateTimeBeforeOrderByUpdateTimeAsc(
			@Temporal(TemporalType.TIMESTAMP) Date updateTime
	);

}
