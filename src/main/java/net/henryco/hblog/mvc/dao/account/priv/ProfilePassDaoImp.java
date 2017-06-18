package net.henryco.hblog.mvc.dao.account.priv;

import net.henryco.hblog.mvc.model.account.ProfilePassword;
import net.henryco.hblog.mvc.repository.PasswordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Henry on 18/06/17.
 */
@Repository
public class ProfilePassDaoImp implements ProfilePassDao {

	private final PasswordsRepository passwordsRepository;

	@Autowired
	public ProfilePassDaoImp(PasswordsRepository passwordsRepository) {
		this.passwordsRepository = passwordsRepository;
	}

	@Override
	public ProfilePassword getUserProfilePassword(long id) {
		return passwordsRepository.findOne(id);
	}

	@Override
	public void saveUserProfilePassword(ProfilePassword profilePassword) {
		passwordsRepository.saveAndFlush(profilePassword);
	}

	@Override
	public void deletePassword(long id) {
		passwordsRepository.delete(id);
	}


	@Override
	public void setPassword(ProfilePassword profilePassword) {
		ProfilePassword password = passwordsRepository.findOne(profilePassword.getId());
		password.setPassword(profilePassword.getPassword());
	}

	@Override
	public boolean isPasswordExists(long id) {
		return passwordsRepository.exists(id);
	}
}
