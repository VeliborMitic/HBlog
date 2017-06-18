package net.henryco.hblog.mvc.repository;

import net.henryco.hblog.mvc.model.account.BaseUserProfile;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Henry on 18/06/17.
 */
public interface BaseUserProfileRepository extends JpaRepository<BaseUserProfile, Long> {


	List<BaseUserProfile> findAllByIdIsNotNullOrderByIdAsc(Pageable pageable);

	BaseUserProfile findByUserNameOrEmail(String userName, String email);

}
