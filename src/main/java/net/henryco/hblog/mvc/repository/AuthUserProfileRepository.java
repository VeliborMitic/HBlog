package net.henryco.hblog.mvc.repository;

import net.henryco.hblog.mvc.model.dto.account.AuthUserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Henry on 18/06/17.
 */
public interface AuthUserProfileRepository extends JpaRepository<AuthUserProfile, Long> {
}
