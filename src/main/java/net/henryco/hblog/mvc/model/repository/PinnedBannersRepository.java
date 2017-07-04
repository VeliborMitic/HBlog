package net.henryco.hblog.mvc.model.repository;

import net.henryco.hblog.mvc.model.entity.extra.PinnedBanner;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

/**
 * @author Henry on 17/06/17.
 */
public interface PinnedBannersRepository extends JpaRepository<PinnedBanner, Long> {

	List<PinnedBanner> findByActualIsTrueOrderByIdDesc(Pageable pageable);
	List<PinnedBanner> findByActualIsTrueOrderByIdAsc(Pageable pageable);
	List<PinnedBanner> findByActualIsFalseOrderByIdDesc(Pageable pageable);
	List<PinnedBanner> findByActualIsFalseOrderByIdAsc(Pageable pageable);

}
