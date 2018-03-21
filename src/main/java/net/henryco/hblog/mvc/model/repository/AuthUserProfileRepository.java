package net.henryco.hblog.mvc.model.repository;

import net.henryco.hblog.mvc.model.entity.account.AuthUserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Henry on 18/06/17.
 */
public interface AuthUserProfileRepository extends JpaRepository<AuthUserProfile, Long> {
}
