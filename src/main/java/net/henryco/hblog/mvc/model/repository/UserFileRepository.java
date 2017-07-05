package net.henryco.hblog.mvc.model.repository;

import net.henryco.hblog.mvc.model.entity.account.files.BaseUserFile;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Henry on 05/07/17.
 */
public interface UserFileRepository extends JpaRepository<BaseUserFile, Long> {

	List<BaseUserFile> getAllByUserIDOrderByUpdateTimeDesc(long userID);
	List<BaseUserFile> getAllByUserIDOrderByUpdateTimeDesc(long userID, Pageable pageable);
}
