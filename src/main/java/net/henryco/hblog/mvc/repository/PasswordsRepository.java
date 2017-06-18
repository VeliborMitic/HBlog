package net.henryco.hblog.mvc.repository;

import net.henryco.hblog.mvc.model.account.ProfilePassword;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Henry on 18/06/17.
 */
public interface PasswordsRepository extends JpaRepository<ProfilePassword, Long> {
}
