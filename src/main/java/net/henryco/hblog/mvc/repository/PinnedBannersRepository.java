package net.henryco.hblog.mvc.repository;

import net.henryco.hblog.mvc.model.promo.PinnedBanners;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

/**
 * @author Henry on 17/06/17.
 */
public interface PinnedBannersRepository extends JpaRepository<PinnedBanners, Long> {

	List<PinnedBanners> findByActualIsTrueOrderByIdDesc(Pageable pageable);
	List<PinnedBanners> findByActualIsTrueOrderByIdAsc(Pageable pageable);
	List<PinnedBanners> findByActualIsFalseOrderByIdDesc(Pageable pageable);
	List<PinnedBanners> findByActualIsFalseOrderByIdAsc(Pageable pageable);

}
