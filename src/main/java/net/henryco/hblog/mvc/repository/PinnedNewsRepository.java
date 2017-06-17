package net.henryco.hblog.mvc.repository;

import net.henryco.hblog.mvc.model.promo.PinnedNews;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Henry on 17/06/17.
 */
public interface PinnedNewsRepository extends JpaRepository<PinnedNews, Long> {

	List<PinnedNews> findByActualIsTrueOrderByIdDesc(Pageable pageable);
	List<PinnedNews> findByActualIsTrueOrderByIdAsc(Pageable pageable);
	List<PinnedNews> findByActualIsFalseOrderByIdDesc(Pageable pageable);
	List<PinnedNews> findByActualIsFalseOrderByIdAsc(Pageable pageable);
}
